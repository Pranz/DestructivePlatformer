package me.EdwJes.main.objects.Entities;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import me.EdwJes.main.Main;
import me.EdwJes.main.input.Control;
import me.EdwJes.main.objects.InteractiveObject;

public class Entity extends InteractiveObject {
	
	public double jumpPower = 11, 
			speed = 0.7, 
			friction = 0.3, 
			maxSpeed = 5,
			maxFallSpeed = 17,
			vspeed = 0, 
			hspeed = 0,
			gravity = 0.4;
	
	Control control;
	

	public Entity(float x, float y){
		setInput();
		this.x=x;
		this.y=y;
		hitbox=new Rectangle(x, y, 32, 64);
	}

	@Override
	public void update() {
		super.update();
		ApplyForces();
		move();

	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.white);
		g.draw(getHitbox());
	}
	
	
	
	public void ApplyForces(){

		if(vspeed >= (maxFallSpeed-gravity)){
			vspeed = maxSpeed;
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
	
	protected void move(){
		x += hspeed;
		y += vspeed;
	}
	
	private void setInput(){
		control = new Control(){

			@Override
			public void keyPressed(int key, char keyChar) {
				switch(key){
					case Input.KEY_Z:
						vspeed -= jumpPower;
						break;
				}
				
			}

			@Override
			public void keyReleased(int arg0, char arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void inputEnded() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void inputStarted() {
				// TODO Auto-generated method stub
			}



			@Override
			public void setInput(Input arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		};
	}
}
