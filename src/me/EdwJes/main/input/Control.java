package me.EdwJes.main.input;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.DestructiveGame;

import org.newdawn.slick.KeyListener;

public abstract class Control implements KeyListener {
	
	public static List<Control> list = new ArrayList<Control>();

	public Control() {
		list.add(this);
		DestructiveGame.input.addKeyListener(this);
	}
	
	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

}
