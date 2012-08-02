package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.input.PlayerInput;
import me.EdwJes.main.objects.entities.Entity;

public class View extends Updater {
	public static List<View> list = new ArrayList<View>();
	
	public float x = 0;
	public float y = 0;
	double speed = 5;
	
	public boolean isFollowing = false;
	int followID;
	
	public void followObject(Entity target){
		isFollowing = true;
	}
	

	@Override
	public void update(){
		if(isFollowing){
			x = PlayerInput.getPlayer(followID).ent.x;
			y = PlayerInput.getPlayer(followID).ent.y;
		}
	}


	public void followPlayer(int ID) {
		isFollowing = true;
		followID = ID;
		
	}
}
