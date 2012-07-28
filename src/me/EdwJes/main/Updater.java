package me.EdwJes.main;

import java.util.List;
import java.util.ArrayList;

public abstract class Updater {
	
	boolean activated;
	
	public static List<Updater> list = new ArrayList<Updater>();
	
	public Updater(){
		activated = true;
		list.add(this);
		
	}
	
	public abstract void update();
	
	final void callUpdate(){
		if(activated){
			update();
		}
	}
	
	public final void destroy(){
		noUpdate();
		activated = false;
		onDestroy();
	}
	
	public void onDestroy(){}
	
	public void noUpdate(){
		if(list.contains(this))
			list.remove(this);
	}

}
