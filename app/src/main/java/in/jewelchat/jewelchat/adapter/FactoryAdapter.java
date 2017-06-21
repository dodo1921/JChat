package in.jewelchat.jewelchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import in.jewelchat.jewelchat.R;
import in.jewelchat.jewelchat.models.Factory;

/**
 * Created by mayukhchakraborty on 17/06/17.
 */

public class FactoryAdapter extends RecyclerView.Adapter<FactoryAdapter.MyViewHolder> {

	private Context mContext;
	private List<Factory> factoryList;

	public FactoryAdapter(Context mContext, List<Factory> factoryList) {
		this.mContext = mContext;
		this.factoryList = factoryList;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.activity_factories_element, parent, false);

		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		Factory f = this.factoryList.get(position);
		//int x;
		//if(f.factory_type == 1)
		holder.factory_image.setImageResource(R.drawable.t3);

	}

	@Override
	public int getItemCount() {
		return this.factoryList.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		//public TextView title, count;
		//public ImageView thumbnail, overflow;
		public ImageView factory_image;

		public MyViewHolder(View view) {
			super(view);
			factory_image = (ImageView) view.findViewById(R.id.thumbnail);
			//title = (TextView) view.findViewById(R.id.title);
			//count = (TextView) view.findViewById(R.id.count);
			//thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
			//overflow = (ImageView) view.findViewById(R.id.overflow);
		}
	}
}
