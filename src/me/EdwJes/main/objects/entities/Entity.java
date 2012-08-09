package me.EdwJes.main.objects.entities;


import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
			gravity = 0.4,
			vspeed = 0, 
			hspeed = 0;

	public Entity(float x, float y){
		super(x, y);
		hitbox=new Rectangle(x, y, 32, 64);
		solid = false;
	}

	@Override
	public void update() {
		super.update();
		ApplyForces();
		move((float)hspeed, (float)vspeed);

	}
	
	public void jump(){
		vspeed = -jumpPower;
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
	
	public boolean placeMeeting(float x, float y){
		Shape aBox = getHitbox();
		aBox.setX(x);
		aBox.setY(y);
		for(InteractiveObject o: InteractiveObject.list){
			if(o != this && aBox.intersects(o.getHitbox()))return true;
		}
		return false;
	}
	
	public boolean onGround(){
		return placeMeeting(x, y + 1);
	}
	
	public void ApplyForces(){

		if(vspeed >= (maxFallSpeed-gravity)){
			vspeed = maxFallSpeed;
		}
		else{
			if(!onGround())vspeed += gravity;
		}
		
		if(hspeed > maxSpeed || hspeed < maxSpeed*-1){
			hspeed = Math.signum(hspeed)*maxSpeed;
		}
		else{
			hspeed = Main.increaseTowardsZero(hspeed, friction);
		}
	}
	
	protected void move(float dx, float dy){
		if(dx != 0 || dy != 0){
			if(!placeMeeting(x, y + dy)){
				y += dy;
			}
			else{
				while(!placeMeeting(x, y + Math.signum(dy))){
					y += Math.signum(dy);
				}
				vspeed = 0;
			}
			if(!placeMeeting(x + dx, y)){
				x += dx;
			}
			else{
				while(!placeMeeting(x + Math.signum(dx), y)){
					x += Math.signum(dx);
				}
				hspeed = 0;
			}

		}
	}
}
