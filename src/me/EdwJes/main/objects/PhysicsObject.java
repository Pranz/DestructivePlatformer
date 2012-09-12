package me.EdwJes.main.objects;

import me.EdwJes.main.Main;

import org.newdawn.slick.geom.Shape;

/**
 * Any object affected by basic physics like gravity, vertical and horizontal speed.
 * 
 */

public abstract class PhysicsObject extends InteractiveObject {
	
	public double
	gravity,
	hspeed,
	vspeed,
	maxFallSpeed,
	maxSpeed,
	friction;

	public PhysicsObject(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void update(){
		super.update();
		applyForces();
		move((float)hspeed, (float)vspeed);
	}
	
	public boolean onGround(){
		return placeMeeting(x, y + 1, true);
	}
	
	protected void move(float dx, float dy){
		if(solid){
			if(!placeMeeting(x, y + dy, true)){
				y += dy;
			}
			else{
				while(!placeMeeting(x, y + Math.signum(dy), true)){
					y += Math.signum(dy);
				}
				vspeed = 0;
			}
			if(!placeMeeting(x + dx, y, true)){
				x += dx;
			}
			else{
				while(!placeMeeting(x + Math.signum(dx), y, true)){
					x += Math.signum(dx);
				}
				hspeed = 0;
			}
		}
		else{
			x += dx;
			y += dy;
		}
	}
	
	public boolean placeMeeting(float x, float y, boolean onlySolids){
		Shape aBox = getHitbox();
		aBox.setX(x);
		aBox.setY(y);
		for(InteractiveObject o: InteractiveObject.list){
			if(o != this && (!onlySolids || o.solid) && aBox.intersects(o.getHitbox()))return true;
		}
		return false;
	}
	
	public void applyForces(){
		if(vspeed >= (maxFallSpeed-gravity * Main.getGravityModifier())){
			vspeed = maxFallSpeed;
		}
		else{
			if(!onGround())vspeed += gravity * Main.getGravityModifier();
		}
		
		if(Math.abs(hspeed) > getMaxSpeed()){
			hspeed = Math.signum(hspeed) * getMaxSpeed();
		}
		else{
			hspeed = Main.increaseTowardsZero(hspeed, friction);
		}
	}
	
	public double getMaxSpeed(){
		return maxSpeed;
	}
	
	public double lengthDirX(double length, double theta){
		return Math.cos(theta) * length;
	}
	
	public double lengthDirY(double length, double theta){
		return Math.sin(theta) * length;
	}
	
	public void setSpeed(double speed, double direction){
		hspeed = lengthDirX(speed, direction);
		vspeed = lengthDirY(speed, direction);
	}

}
