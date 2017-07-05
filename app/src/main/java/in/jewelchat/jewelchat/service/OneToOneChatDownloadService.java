package in.jewelchat.jewelchat.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.firebase.crash.FirebaseCrash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.jewelchat.jewelchat.JewelChatApp;
import in.jewelchat.jewelchat.JewelChatPrefs;
import in.jewelchat.jewelchat.JewelChatURLS;
import in.jewelchat.jewelchat.database.database_crud.InsertNewGroupMessage;
import in.jewelchat.jewelchat.database.database_crud.InsertNewMessage;
import in.jewelchat.jewelchat.database.database_crud.UpdateDeliveryAck;
import in.jewelchat.jewelchat.database.database_crud.UpdateGroupDeliveryAck;
import in.jewelchat.jewelchat.database.database_crud.UpdateGroupMessageDelivered;
import in.jewelchat.jewelchat.database.database_crud.UpdateGroupMessageRead;
import in.jewelchat.jewelchat.database.database_crud.UpdateGroupReadAck;
import in.jewelchat.jewelchat.database.database_crud.UpdateMessageDelivered;
import in.jewelchat.jewelchat.database.database_crud.UpdateMessageRead;
import in.jewelchat.jewelchat.database.database_crud.UpdatePublishAck;
import in.jewelchat.jewelchat.database.database_crud.UpdatePublishGroupAck;
import in.jewelchat.jewelchat.database.database_crud.UpdateReadAck;
import in.jewelchat.jewelchat.network.JewelChatRequest;
import in.jewelchat.jewelchat.util.NetworkConnectivityStatus;

/**
 * Created by mayukhchakraborty on 22/06/17.
 */

public class OneToOneChatDownloadService extends IntentService
		implements Response.ErrorListener, Response.Listener<JSONObject>  {

	public OneToOneChatDownloadService() {
		super("OneToOneChatDownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Bundle b = intent.getExtras();
		int page = b.getInt("page");

		JSONObject t = new JSONObject();
		try {
			t.put("created_at", JewelChatApp.getSharedPref().getLong(JewelChatPrefs.LAST_OTO_MSG, 0));
			t.put("page", page);
			JewelChatRequest req = new JewelChatRequest(Request.Method.POST, JewelChatURLS.GETALLCHATMESSAGES, t, this, this);
			if (NetworkConnectivityStatus.getConnectivityStatus() == NetworkConnectivityStatus.CONNECTED)
				JewelChatApp.getRequestQueue().add(req);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onErrorResponse(VolleyError error) {
		String errorMessage = error.toString();
		NetworkResponse response = error.networkResponse;

		if(response != null && response.data != null){
			if(response.statusCode == 403){

				SharedPreferences.Editor editor = JewelChatApp.getSharedPref().edit();
				editor.putBoolean(JewelChatPrefs.IS_LOGGED, false);
				editor.putString(JewelChatPrefs.MY_ID, "");
				editor.commit();


			}else if(response.statusCode == 500){

				String json = new String(response.data);
				try{
					JSONObject obj = new JSONObject(json);
					errorMessage = "Please Try Again. Error 500. "+obj.getString("data");
					//makeToast(errorMessage);

				} catch(JSONException e){
					e.printStackTrace();

				}

			}else{

				if (error instanceof TimeoutError || error instanceof NoConnectionError) {
					errorMessage = "Connection Timeout";
				}else{
					errorMessage = "Network Error";
				}

			}
		}

		JewelChatApp.appLog("OneToOneChatDownloadService Volley error:"+errorMessage);
		FirebaseCrash.report(error);
	}

	@Override
	public void onResponse(JSONObject response) {
		JewelChatApp.appLog("OneToOneChatDownloadService" + ":onResponse");
		try {

			Boolean error = response.getBoolean("error");
			if(error){
				String err_msg = response.getString("message");
				throw new Exception(err_msg);
			}
			int page = response.getInt("pageno");
			JSONArray chats = response.getJSONArray("chats");

			String eventname; JSONObject packet = null;
			for(int i=0; i<chats.length(); i++){
				//process messages

					packet = chats.getJSONObject(i);
					eventname = packet.getString("eventname");

					switch(eventname){
						case "new_msg": new InsertNewMessage(packet); break;
						case "publish_ack": new UpdatePublishAck(packet); break;
						case "msg_delivery": new UpdateMessageDelivered(packet); break;
						case "delivery_ack": new UpdateDeliveryAck(packet); break;
						case "msg_ack": new UpdateMessageRead(packet); break;
						case "read_ack": new UpdateReadAck(packet); break;
						case "new_group_msg": new InsertNewGroupMessage(packet); break;
						case "publish_group_ack": new UpdatePublishGroupAck(packet); break;
						case "group_msg_delivery": new UpdateGroupMessageDelivered(packet); break;
						case "group_delivery_ack": new UpdateGroupDeliveryAck(packet); break;
						case "group_msg_ack": new UpdateGroupMessageRead(packet); break;
						case "group_read_ack": new UpdateGroupReadAck(packet); break;
					}

			}

			if(page != -1){
				Bundle b = new Bundle();
				b.putInt("page", page);
				Intent service = new Intent(getApplicationContext(), OneToOneChatDownloadService.class);
				service.putExtras(b);
				startService(service);

			}else{

				JewelChatApp.getSharedPref().edit().putLong(JewelChatPrefs.LAST_OTO_MSG, response.getLong("created_at"));

			}




		} catch (JSONException e) {
			FirebaseCrash.report(e);
		} catch (Exception e) {
			FirebaseCrash.report(e);
		}

	}
}
