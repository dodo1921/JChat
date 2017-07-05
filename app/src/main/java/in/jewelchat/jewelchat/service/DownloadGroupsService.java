package in.jewelchat.jewelchat.service;

import android.app.IntentService;
import android.content.Intent;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by mayukhchakraborty on 04/07/17.
 */

public class DownloadGroupsService extends IntentService
		implements Response.ErrorListener, Response.Listener<JSONObject>  {

	public DownloadGroupsService() {
		super("DownloadGroupsService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

	}

	@Override
	public void onErrorResponse(VolleyError error) {

	}

	@Override
	public void onResponse(JSONObject response) {

	}
}
