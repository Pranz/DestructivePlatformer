package me.EdwJes.main.input;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.Main;
import me.EdwJes.main.Updater;

import org.newdawn.slick.KeyListener;

public abstract class Control extends Updater implements KeyListener {
	
	public static List<Control> list = new ArrayList<Control>();
	List<Integer> pressedKeys = new ArrayList<Integer>();

	public Control() {
		list.add(this);
		Main.input.addKeyListener(this);
	}
	
	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void isKeyDown(){
		
	}
	
	@Override
	public void keyReleased(int key, char keyChar) {
		if(pressedKeys.contains(key))pressedKeys.remove((Integer)key);
	}
	
	@Override
	public void keyPressed(int key, char keyChar) {
		if(!pressedKeys.contains(key))pressedKeys.add(key);
	}
	
	@Override
	public void update(){
		if(pressedKeys.size() != 0) isKeyDown();
	}

}
