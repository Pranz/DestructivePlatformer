package me.EdwJes.main.objects;

import me.EdwJes.main.View;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class InteractiveObject extends RenderableObject{
	private float x,y;
	public Shape hitbox;
	
	protected void updateHitbox(){
		hitbox.setX(getX());
		hitbox.setY(getY());
	}
	
	@Override
	public void render(Graphics g, View view){
		g.setColor(Color.white);
		Shape tempBox = hitbox;
		tempBox.setLocation(tempBox.getX() + view.x, tempBox.getY() + view.y);
		g.draw(tempBox);
	}
	
	@Override
	public void update() {
		updateHitbox();
	}
	
	public Shape getHitbox(){
		return hitbox;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
