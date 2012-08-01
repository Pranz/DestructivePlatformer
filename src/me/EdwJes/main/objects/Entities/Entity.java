package me.EdwJes.main.objects.Entities;


import org.newdawn.slick.geom.Rectangle;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.InteractiveObject;

public class Entity extends InteractiveObject {
	
	public final int RIGHT = 1;
	public final int LEFT = -1;
	
	public double jumpPower = 11, 
			speed = 0.7, 
			friction = 0.3, 
			maxSpeed = 5,
			maxFallSpeed = 17,
			vspeed = 0, 
			hspeed = 0,
			gravity = 0.4;

	public Entity(float x, float y){
		this.setX(x);
		this.setY(y);
		hitbox=new Rectangle(x, y, 32, 64);
	}

	@Override
	public void update() {
		super.update();
		ApplyForces();
		move((float)hspeed, (float)vspeed);

	}
	
	public void jump(){
		vspeed -= jumpPower;
	}
	
	public void walk(int direction){
		switch(direction){
		case RIGHT:
			hspeed += speed;
			break;
		
		case LEFT:
			hspeed -= speed;
			break;
		}
	}
	
	public void ApplyForces(){

		if(vspeed >= (maxFallSpeed-gravity)){
			vspeed = maxFallSpeed;
		}
		else{
			vspeed += gravity;
		}
		
		if(hspeed > maxSpeed || hspeed < maxSpeed*-1){
			hspeed = Math.signum(hspeed)*maxSpeed;
		}
		else{
			hspeed = Main.increaseTowardsZero(hspeed, friction);
		}
	}
	
	protected void move(float dx, float dy){
		setX(getX() + dx);
		setY(getY() + dy);
	}
}
