package me.EdwJes.main.objects;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.Updater;

public abstract class GameObject extends Updater {
	public static final int TILE_WIDTH=8,TILE_HEIGHT=8;
	
	public static List<GameObject> list = new ArrayList<GameObject>();
	
	public GameObject(){
		list.add(this);
	}

}
