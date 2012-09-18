package me.EdwJes.main.objects;

import me.EdwJes.main.ListHandler;

import org.newdawn.slick.Graphics;

public abstract class RenderableObject extends GameObject {
	


	public RenderableObject() {
		super();
		ListHandler.get().add(this, ListHandler.RENDERABLEOBJECT);
	}
	
	public abstract void render(Graphics g);
	
	


}
