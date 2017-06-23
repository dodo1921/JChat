package in.jewelchat.jewelchat.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.jewelchat.jewelchat.JewelChatApp;
import in.jewelchat.jewelchat.R;
import in.jewelchat.jewelchat.adapter.AchievementAdapter;
import in.jewelchat.jewelchat.models.Achievement;

import static in.jewelchat.jewelchat.R.id.achivement;

/**
 * Created by mayukhchakraborty on 22/06/17.
 */

public class FragmentAchievements extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{

	private String className;
	private RecyclerView recyclerView;
	private AchievementAdapter achievementAdapter;
	private List<Achievement> achivementList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		className = getClass().getSimpleName();
		JewelChatApp.appLog(className + ":onCreate");
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		JewelChatApp.appLog(className + ":onCreateView");

		View view = inflater.inflate(R.layout.fragment_achievement, container, false);
		recyclerView = (RecyclerView) view.findViewById(achivement);
		achivementList = new ArrayList<Achievement>();
		achievementAdapter = new AchievementAdapter(getContext(), achivementList);
		recyclerView.setAdapter(achievementAdapter);


		return view;

		//return null;
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.i("Task>>>","onResume");
	}



	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i("Fragment>>>", "TASK Destroyed");

	}

	@Override
	public void onErrorResponse(VolleyError error) {

	}

	@Override
	public void onResponse(JSONObject response) {

	}
}
