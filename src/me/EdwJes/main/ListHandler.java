package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;

public class ListHandler {
	
	public static final int
	UPDATER = 0,
	RENDERABLEOBJECT = 1,
	INTERACTIVEOBJECT = 2,
	PHYSICSOBJECT = 3,
	ENTITY = 4,
	BULLET = 5;
	
	static ListHandler listHandler;
	
	@SuppressWarnings("unchecked")
	private List<Updater>[] list = new ArrayList [10];

	
	private ListHandler(){
		initLists();
	}
	
	static public ListHandler get(){
		if(listHandler == null) listHandler = new ListHandler();
		return listHandler;
	}
	
	void initLists(){
		for(int i = 0; i < 10; i++){
			list[i] = new ArrayList<Updater>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Updater> List<T> getList(int l, Class<T> type){
		return (List<T>) list[l];
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Updater> List<T> getList(int l){
		return (List<T>) list[l];
	}
	
	public void add(Updater o, int l){
		list[l].add(o);
	}
	
	public void add(Updater o){
		list[UPDATER].add(o);
	}
	
	public void remove(Updater obj){
		for(int i = 0; i < 10; i++){
			if (list[i].contains(obj)) list[i].remove(obj);
		}
	}
	
	public void remove(Updater obj, int l){
		if (l > 0){
			list[l].remove(obj);
		}
		list[0].remove(obj);
	}

}
