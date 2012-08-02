package me.EdwJes.main.objects;

import java.util.ArrayList;
import java.util.List;

import me.EdwJes.main.View;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class InteractiveObject extends RenderableObject{
	public float x, y;
	
	public Shape hitbox;
	public boolean solid;
	
	static public List<InteractiveObject> list = new ArrayList<InteractiveObject>();
	
	public InteractiveObject(float x, float y){
		list.add(this);
		this.x = x;
		this.y = y;
	}
	
	protected void updateHitbox(){
		hitbox.setX(x);
		hitbox.setY(y);
	}
	
	@Override
	public void render(Graphics g, View view){
		g.setColor(Color.white);
		Shape tempBox = hitbox;
		g.draw(tempBox);
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
}
