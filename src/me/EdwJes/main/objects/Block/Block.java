package me.EdwJes.main.objects.Block;

import org.newdawn.slick.geom.Rectangle;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.InteractiveObject;

public class Block extends InteractiveObject{
	private int width=1,height=1;
	
	public Block(){}
	
	public Block(float x, float y, int width,int height){
		setX(x);
		setY(y);
		this.width=width;
		this.height=height;
		hitbox = new Rectangle(getX(), getY(), width * Main.TILE_SIZE, height * Main.TILE_SIZE);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
