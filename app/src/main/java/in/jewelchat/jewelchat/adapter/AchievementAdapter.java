package in.jewelchat.jewelchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import in.jewelchat.jewelchat.models.Achievement;

/**
 * Created by mayukhchakraborty on 22/06/17.
 */

public class AchievementAdapter  extends RecyclerView.Adapter<AchievementAdapter.MyViewHolder>  {

	private Context mContext;
	private List<Achievement> achievementList;



	public AchievementAdapter(Context context, List<Achievement> achievementList) {
		this.mContext = mContext;
		this.achievementList = achievementList;
	}

	@Override
	public AchievementAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(AchievementAdapter.MyViewHolder holder, int position) {

	}



	@Override
	public int getItemCount() {
		return 0;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		//public TextView title, count;
		//public ImageView thumbnail, overflow;
		public ImageView factory_image;

		public MyViewHolder(View view) {
			super(view);
			//factory_image = (ImageView) view.findViewById(R.id.thumbnail);
			//title = (TextView) view.findViewById(R.id.title);
			//count = (TextView) view.findViewById(R.id.count);
			//thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
			//overflow = (ImageView) view.findViewById(R.id.overflow);
		}
	}
}
