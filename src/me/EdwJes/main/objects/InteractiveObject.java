package me.EdwJes.main.objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class InteractiveObject extends RenderableObject{
	public float x, y, prevX, prevY;
	
	public Shape hitbox;
	public boolean solid;
	
	static public List<InteractiveObject> list = new ArrayList<InteractiveObject>();
	
	public InteractiveObject(float x, float y){
		list.add(this);
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
}
