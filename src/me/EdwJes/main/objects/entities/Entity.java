package me.EdwJes.main.objects.entities;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;

import me.EdwJes.main.Alarm;
import me.EdwJes.main.ListHandler;
import me.EdwJes.main.Main;
import me.EdwJes.main.objects.PhysicsObject;
import me.EdwJes.main.objects.entities.action.Action;
import me.EdwJes.main.objects.entities.action.MeleeAttack;

public class Entity extends PhysicsObject {
	
	public final int RIGHT = 1,
	LEFT = -1,
	ACTIONS = 2;
	
	
	public int width  = 32;
	public int height = 64;
	public int walking;
	public boolean immobilized;
	
	public Action[] action;
	
	public List<Double> speedMods = new ArrayList<Double>();
	
	public double jumpPower = 11,
	speed = 0.7;
	
	Entity ent = this;

	public Entity(float x, float y){
		super(x, y);
		ListHandler.get().add(this, ListHandler.ENTITY);
		action = new Action[ACTIONS];
		action[0] = new MeleeAttack(this);
		action[1] = new MeleeAttack(this);
		hitbox=new Rectangle(x, y, width, height);
		solid = true;
		immobilized = false;
		walking = 0;

		friction = 0.3; 
		maxSpeed = 5;
		maxFallSpeed = 17;
		gravity = 0.4;
		vspeed = 0;
		hspeed = 0;
		

		

	}
	
	@Override
	public void update(){
		super.update();
		walking = 0;
	}
	
	@Override
	protected void updateHitbox(){
		hitbox.setX(x - width /2);
		hitbox.setY(y - height/2);
	}
	
	public void jump(){
		if(onGround() && !immobilized){
			vspeed = -jumpPower;
		}
	}
	
	public void walk(int direction){
		if(!immobilized){
			switch(direction){
				case RIGHT:
					if((Math.abs(hspeed)) < getMaxSpeed()) hspeed += getSpeed();
					walking = 1;
					break;
		
				case LEFT:
					if((Math.abs(hspeed)) < getMaxSpeed()) hspeed -= getSpeed();
					walking = -1;
					break;
			}
		}
	}
	
	
	public void applyForces(){
		//TODO friction tillämpas ej om speedmodifier är 0 eller har negativt värde..
		if(vspeed >= (maxFallSpeed-gravity * Main.getGravityModifier())){
			vspeed = maxFallSpeed;
		}
		else{
			if(!onGround())vspeed += gravity * Main.getGravityModifier();
		}
		
		//if(Math.abs(hspeed) > getMaxSpeed() && Math.abs((Math.abs(hspeed) - getMaxSpeed())) < getSpeed()){
		if(((Math.abs(hspeed + getSpeed()*walking) > getMaxSpeed()) && (Math.abs(hspeed) - speed) < getMaxSpeed())){
			hspeed = Math.signum(hspeed) * getMaxSpeed();
		}
		else{
			hspeed = Main.increaseTowardsZero(hspeed, friction);
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
	
	public double getSpeed(){
		return speed * (1 + sumOfList(speedMods));
	}
	
	@Override
	public double getMaxSpeed(){
		return maxSpeed * (1 + sumOfList(speedMods));
	}

}
