package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.input.PlayerInput;
import me.EdwJes.main.objects.Entities.Entity;



/**
* View Class
* 
* <P>The view port system. 
*  
* <P>Testing links, class: {@link BigDecimal} 
* See constructor {@link #posMoveX(int)} for more information.
*  
* @author Lolirofle
* @version 1.0
*/
public class View extends Updater {
	public static List<View> list = new ArrayList<View>();
	
	public float x = 0;
	public float y = 0;
	double speed = 5;
	
	public boolean isFollowing = false;
	Entity target;
	
	public void followObject(Entity target){
		isFollowing = true;
		this.target = target;
	}
	

	@Override
	public void update(){
		if(isFollowing){
			x = target.getX();
			y = target.getY();
		}
	}


	public void followPlayer(PlayerInput player) {
		followObject(player.ent);
		
	}
}
