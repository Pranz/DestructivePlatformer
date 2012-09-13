package me.EdwJes.main.objects.bullets;

import me.EdwJes.main.Main;
import me.EdwJes.main.objects.InteractiveObject;

public abstract class MovingBullet extends Bullet {
	
	public double
	hspeed,
	vspeed,
	gravity, 
	friction;
	
	public MovingBullet(int x, int y, int lifespan, boolean relativePos, double hspeed, double vspeed, 
			double gravity, double friction, InteractiveObject host){

		
		super(x, y, lifespan, relativePos, host);
		
		this.hspeed   = hspeed;
		this.vspeed   = vspeed;
		this.gravity  = gravity;
		this.friction = friction;
	}
	
	@Override
	public void update(){
		super.update();
		applyForces();
	}
	
	public void applyForces(){
		x += hspeed;
		y += vspeed;
		vspeed += gravity;
		hspeed = Main.increaseTowardsZero(hspeed, friction);
	}

}
