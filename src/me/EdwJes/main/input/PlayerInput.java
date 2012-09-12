package me.EdwJes.main.input;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.entities.Entity;

import org.newdawn.slick.Input;

public class PlayerInput {

	public int KEY_ENTER = Input.KEY_ENTER;

	static int inputs = 0;
	
	Control control;
	int playerID;
	public Entity ent;
	

	public static List<PlayerInput> list = new ArrayList<PlayerInput>();
	
	public int 
	KEY_RIGHT,
	KEY_LEFT,
	KEY_UP,
	KEY_DOWN,
	KEY_JUMP,
	KEY_ACTION1,
	KEY_ACTION2;
	
	int KEY_ACTION[] = new int[2];
	
	public boolean acceptsInput = true;
	
	public PlayerInput(Entity ent){
		
		list.add(this);
		playerID = inputs;
		inputs++;
		setInput();
		this.ent = ent;
		
		KEY_RIGHT     = Input.KEY_RIGHT;
		KEY_LEFT      = Input.KEY_LEFT;
		KEY_UP        = Input.KEY_UP;
		KEY_DOWN      = Input.KEY_DOWN;
		KEY_JUMP      = Input.KEY_Z;
		KEY_ACTION[0] = Input.KEY_X;
		KEY_ACTION[1] = Input.KEY_SPACE;
		
	}
	
	public static PlayerInput getPlayer(int ID){
		return list.get(ID);
	}
	
	private void setInput(){
		control = new Control(){

			@Override
			public void keyPressed(int key, char keyChar) {
				super.keyPressed(key, keyChar);
				if(acceptsInput){
					
					if(key == KEY_JUMP){
						ent.jump();
					}
					
					for(int i=0; i<KEY_ACTION.length; i++){
						if(key == KEY_ACTION[i]){
							ent.action[i].onKeyPress();
						}
					}
				}
			}
			
			@Override
			public void isKeyDown(){
				if(acceptsInput){
					if(pressedKeys.contains(KEY_RIGHT)){
						ent.walk(ent.RIGHT);
					}
					if(pressedKeys.contains(KEY_LEFT)){
						ent.walk(ent.LEFT);
					}
					if(pressedKeys.contains(Input.KEY_D)){
						Main.view.x -= 2;
					}
					if(pressedKeys.contains(Input.KEY_A)){
						Main.view.x += 2;
					}
					
					for(int i=0; i<KEY_ACTION.length; i++){
						if(pressedKeys.contains(KEY_ACTION[i])){
							ent.action[i].onKeyDown();
						}
					}
				}
			}
			
			@Override
			public void keyReleased(int key, char keyChar) {
				super.keyReleased(key, keyChar);
				if(acceptsInput){
					
					for(int i=0; i<KEY_ACTION.length; i++){
						if(key == KEY_ACTION[i]){
							ent.action[i].onKeyRelease();
						}
					}
				}
			}

			@Override
			public void inputEnded() {
			}

			@Override
			public void inputStarted() {
				
			}

			@Override
			public void setInput(Input arg0) {
				
			}
		};
	}
}
