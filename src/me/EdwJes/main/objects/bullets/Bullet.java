package me.EdwJes.main.objects.bullets;

import java.util.List;

import me.EdwJes.main.objects.InteractiveObject;
import me.EdwJes.main.objects.entities.Entity;

/**
 * Any body causing hits.
 * 
 * @param relativePos If the position is relative to the host, i.e if the host moves by [dx, dy], the bullet moves by [dx, dy]
 * @param lifespan The amount of frames the bullet is alive. Enter 0 for infinite. You should use a custom destroy system in this case.
 */

public abstract class Bullet extends InteractiveObject {
	
	public InteractiveObject host;
	public boolean relativePos;
	public int lifespan;
	int timeAlive = 0;

	public Bullet(float x, float y, int lifespan, boolean relativePos, InteractiveObject host) {
		super(x, y);
		this.lifespan = lifespan;
		this.relativePos = relativePos;
		this.host = host;
	}
	
	@Override
	public void update(){
		super.update();
		
		timeAlive++;
		
		if(placeMeetingEntity(x, y)) hitEntities(getAllPlaceMeetingEntity(x, y));
		
		if(relativePos){
			x += host.prevX;
			y += host.prevY;
		}
		
		if(timeAlive > lifespan && lifespan != 0)destroy();
	}
	
	public int getTimeAlive(){
		return timeAlive;
	}
	
	protected abstract void hitEntity(Entity ent);
	
	void hitEntities(List<Entity> entList){
		for(Entity ent: entList){
			hitEntity(ent);
		}
	}
	

}
