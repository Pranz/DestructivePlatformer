package me.EdwJes.main.objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

public abstract class RenderableObject extends GameObject {
	
	public static List<RenderableObject> list = new ArrayList<RenderableObject>();

	public RenderableObject() {
		list.add(this);
	}
	
	public abstract void render(Graphics g);


}
