package me.EdwJes.main.objects;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.ListHandler;
import me.EdwJes.main.objects.entities.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class InteractiveObject extends RenderableObject{
	public float x, y, prevX, prevY;
	
	public Shape hitbox;
	public boolean solid;

	public InteractiveObject(float x, float y){
		super();
		ListHandler.get().add(this, ListHandler.INTERACTIVEOBJECT);
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
	}
	
	protected void updateHitbox(){
		hitbox.setX(x);
		hitbox.setY(y);
	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.white);
		g.draw(hitbox);
	}
	
	@Override
	public void update() {
		updateHitbox();
		setPrevCord();
	}
	
	private void setPrevCord() {
		prevX = x;
		prevY = y;
	}

	public Shape getHitbox(){
		return hitbox;
	}
	
	public boolean placeMeeting(float x, float y, boolean onlySolids){
		Shape aBox = getHitbox();
		aBox.setX(x);
		aBox.setY(y);
		for(InteractiveObject o: ListHandler.get().getList(ListHandler.INTERACTIVEOBJECT, InteractiveObject.class)){
			if(o != this && (!onlySolids || o.solid) && aBox.intersects(o.getHitbox()))return true;
		}
		return false;
	}
	
	public boolean placeMeetingEntity(float x, float y){
		Shape aBox = getHitbox();
		aBox.setX(x);
		aBox.setY(y);
		for(Entity o: ListHandler.get().getList(ListHandler.ENTITY, Entity.class)){
			if(o != this && aBox.intersects(o.getHitbox()))return true;
		}
		return false;
	}
	
	public Entity getPlaceMeetingEntity(float x, float y){
		Shape aBox = getHitbox();
		aBox.setX(x);
		aBox.setY(y);
		for(Entity o: ListHandler.get().getList(ListHandler.ENTITY, Entity.class)){
			if(o != this && aBox.intersects(o.getHitbox()))return o;
		}
		return null;
	}
	
	public List<Entity> getAllPlaceMeetingEntity(float x, float y){
		List<Entity> objList = new ArrayList<Entity>();
		
		Shape aBox = getHitbox();
		aBox.setX(x);
		aBox.setY(y);
		for(Entity o: ListHandler.get().getList(ListHandler.ENTITY, Entity.class)){
			if(o != this && aBox.intersects(o.getHitbox()))objList.add(o);
		}
		return objList;
	}
}
