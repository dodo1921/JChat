package in.jewelchat.jewelchat.service.service_helpers;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import in.jewelchat.jewelchat.JewelChatApp;
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

/**
 * Created by mayukhchakraborty on 03/07/17.
 */

public class LooperThread extends Thread {
	private Handler mHandler;
	private Looper mLooper;
	private String msgchannel;
	private String groupchannel;

	private static final String TAG = "LooperThread";



	public Looper getLooper(){
		return mLooper;
	}

	public Handler getHandler(){
		synchronized(this){
			while (mHandler == null){
				try{
					wait();
				}catch(InterruptedException e){}
			}
		}
		return mHandler;
	}

	static class MyHandler extends Handler{
		public void handleMessage(Message msg){

			JSONObject packet = (JSONObject)msg.obj;

			try {
				String eventname = packet.getString("eventname");

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


			} catch (JSONException e) {
				JewelChatApp.appLog(getClass().getSimpleName()+":"+e);
			}

		}
	}


	public void run(){
		Looper.prepare();
		synchronized(this){
			mHandler = new LooperThread.MyHandler();
			mLooper = Looper.myLooper();
			notifyAll();
		}
		Looper.loop();

	}
}
