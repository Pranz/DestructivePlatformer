package me.EdwJes.main.objects.entities;

import me.EdwJes.main.Alarm;

public abstract class Action {
	
	/**
	 * Any action a entity may perform. 
	 * @author Jesper Fridefors
	 */
	
	int delay,
	afterLag;
	double speedModifier;
	
	Entity ent;
	
	public Action(Entity ent){
		this.ent = ent;
	}
	
	final public void executeAction(){
		if(conditionsAreMet()){

			preAction();
			new Alarm(delay){
				@Override
				public void execute(){

					action();
					new Alarm(afterLag){
						
						@Override
						public void execute(){
							resolve();
						}
					};
				}
			};
		}
	}
	
	public abstract void action();
	public abstract void preAction();
	public abstract void resolve();
	public abstract boolean conditionsAreMet();

}
