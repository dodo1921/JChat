package in.jewelchat.jewelchat.models;

import java.util.ArrayList;

/**
 * Created by mayukhchakraborty on 17/06/17.
 */

public class Factory {

	public int task_id;
	public int factory_type;
	public int duration;
	public int level;
	public int is_on;
	public String start_time;
	public ArrayList<Factory_material> materials;


	public Factory(int id, int factory_type, int level, int duration, int is_on, String start_time, ArrayList<Factory_material> materials){
		this.task_id = id;
		this.factory_type = factory_type;
		this.level = level;
		this.duration = duration;
		this.is_on = is_on;
		this.start_time = start_time;
		this.materials = materials;
	}

}
