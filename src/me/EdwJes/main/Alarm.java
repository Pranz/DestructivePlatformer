package me.EdwJes.main;

public abstract class Alarm extends Updater {
	/**
	 * Alarm, timer.
	 * Whenever you create an alarm you must override
	 * the execute method.
	 * @author Jesper Fridefors
	 * @param maxTicks the amount of frames it takes for the alarm to call the execute() method.
	 */
	
	public int ticks = 0, maxTicks;
	
	public Alarm(int maxTicks){
		super();
		list.add(this);
		this.maxTicks = maxTicks;
	}
	
	@Override public void update(){
		if(ticks == maxTicks) execute();
		else if(ticks < maxTicks) ticks++;
	}
	
	public abstract void execute();
	

	public void loop(){
		ticks = 0;
	}

}
