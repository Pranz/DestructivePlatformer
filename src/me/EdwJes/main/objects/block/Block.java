package me.EdwJes.main.objects.block;

import org.newdawn.slick.geom.Rectangle;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.InteractiveObject;

public class Block extends InteractiveObject{
	private int width=1,height=1;
	
	public Block(int x, int y, int width,int height){
		super(x, y);
		this.width=width;
		this.height=height;
		hitbox = new Rectangle(x, y, width * Main.TILE_SIZE, height * Main.TILE_SIZE);
		solid = true;
		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
