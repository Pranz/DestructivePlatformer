package me.EdwJes.main.objects.bullets;

import me.EdwJes.main.objects.InteractiveObject;
import me.EdwJes.main.objects.entities.Entity;

public abstract class Bullet extends InteractiveObject {
	
	public Entity host;
	public boolean relativePos;

	public Bullet(float x, float y, Entity host) {
		super(x, y);
		this.host = host;
	}
	
	@Override
	public void update(){
		super.update();
		
		if(relativePos){
			x += host.prevX;
			y += host.prevY;
		}
	}

}
