package me.EdwJes.main.objects.entities;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.InteractiveObject;
import me.EdwJes.main.objects.entities.action.Action;
import me.EdwJes.main.objects.entities.action.MeleeAttack;

public class Entity extends InteractiveObject {
	
	public final int RIGHT = 1,
	LEFT = -1,
	ACTIONS = 2;
	
	
	public int width  = 32;
	public int height = 64;
	
	public Action[] action;
	
	public List<Double> speedMods = new ArrayList<Double>();
	
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
		action = new Action[ACTIONS];
		action[0] = new MeleeAttack(this);
		action[1] = new MeleeAttack(this);
		hitbox=new Rectangle(x, y, width, height);
		solid = false;
	}

	@Override
	public void update() {
		super.update();
		setSpeeds();
		ApplyForces();
		move((float)hspeed, (float)vspeed);

	}
	
	@Override
	protected void updateHitbox(){
		hitbox.setX(x - width /2);
		hitbox.setY(y - height/2);
	}
	
	public void jump(){
		if(onGround()){
			vspeed = -jumpPower;
		}
	}
	
	public void walk(int direction){
		switch(direction){
		case RIGHT:
			hspeed += getSpeed();
			break;
		
		case LEFT:
			hspeed -= getSpeed();
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
		
		if(hspeed > getMaxSpeed() || hspeed < getMaxSpeed() * -1){
			hspeed = Math.signum(hspeed) * getMaxSpeed();
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
	
	public void addSpeedBuff(double buff){
		speedMods.add(buff);
	}
	
	public void removeSpeedBuff(double buff){
		speedMods.remove(buff);
	}
	
	public static double sumOfList(List<Double> list) {
	     double sum= 0; 
	     for (double d:list)
	         sum = sum + d;
	     return sum;
	}
	
	private void setSpeeds(){

	}
	
	public double getSpeed(){
		return speed * (1 + sumOfList(speedMods));
	}
	
	public double getMaxSpeed(){
		return maxSpeed * (1 + sumOfList(speedMods));
	}

}
