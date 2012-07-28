package me.EdwJes.main.objects.Block;

import me.EdwJes.main.objects.InteractiveObject;

public abstract class Block extends InteractiveObject{
	private int width=1,height=1;
	
	public Block(){}
	public Block(int width,int height){
		this.width=width;
		this.height=height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
