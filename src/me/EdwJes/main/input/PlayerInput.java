package me.EdwJes.main.input;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.entities.Entity;

import org.newdawn.slick.Input;

public class PlayerInput {

	static int inputs = 0;
	
	Control control;
	int playerID;
	public Entity ent;
	

	public static List<PlayerInput> list = new ArrayList<PlayerInput>();
	
	int 
	KEY_RIGHT,
	KEY_LEFT,
	KEY_UP,
	KEY_DOWN,
	KEY_JUMP,
	KEY_SHOOT,
	KEY_RELOAD;
	
	public PlayerInput(Entity ent){
		
		list.add(this);
		playerID = inputs;
		inputs++;
		setInput();
		this.ent = ent;
		
		KEY_RIGHT = Input.KEY_RIGHT;
		KEY_LEFT  = Input.KEY_LEFT;
		KEY_UP    = Input.KEY_UP;
		KEY_DOWN  = Input.KEY_DOWN;
		KEY_JUMP  = Input.KEY_Z;
		KEY_SHOOT = Input.KEY_X;
		KEY_RELOAD= Input.KEY_R;
		
	}
	
	public static PlayerInput getPlayer(int ID){
		return list.get(ID);
	}
	
	private void setInput(){
		control = new Control(){

			@Override
			public void keyPressed(int key, char keyChar) {
				super.keyPressed(key, keyChar);
				if(key == KEY_JUMP){
					ent.jump();
				}


			}
			
			@Override
			public void isKeyDown(){
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
			}

			@Override
			public void inputEnded() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void inputStarted() {
				// TODO Auto-generated method stub
			}

			@Override
			public void setInput(Input arg0) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
