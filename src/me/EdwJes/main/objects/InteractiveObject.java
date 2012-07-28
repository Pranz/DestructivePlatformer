package me.EdwJes.main.objects;

import org.newdawn.slick.geom.Shape;

public abstract class InteractiveObject extends RenderableObject{
	public float x,y;
	public Shape hitbox;
	
	protected void updateHitbox(){
		hitbox.setX(x);
		hitbox.setY(y);
	}
	
	@Override
	public void update() {
		updateHitbox();
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
}
