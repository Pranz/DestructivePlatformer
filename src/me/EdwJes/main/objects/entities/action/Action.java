package me.EdwJes.main.objects.entities.action;

import me.EdwJes.main.Alarm;
import me.EdwJes.main.objects.entities.Entity;

public abstract class Action {
	
	/**
	 * Any action a entity may perform. 
	 * @author Jesper Fridefors
	 */
	
	protected int delay, afterLag;
	public double speedModifier;
	public Entity ent;
	
	boolean attacking = false;
	
	public Action(Entity ent){
		this.ent = ent;
	}
	
	final public void executeAction(){
		if(conditionsAreMet() && !attacking){
			attacking = true;
			preAction();
			new Alarm(delay){
				@Override
				public void execute(){

					action();
					new Alarm(afterLag){
						
						@Override
						public void execute(){
							resolve();
							attacking = false;
						}
					};
				}
			};
		}
	}
	
	public abstract void preAction();
	public abstract void action();
	public abstract void resolve();
	public abstract void onKeyPress();
	public abstract void onKeyDown();
	public abstract void onKeyRelease();
	
	public abstract boolean conditionsAreMet();
	

}
