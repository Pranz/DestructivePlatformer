package me.EdwJes.main;

public abstract class Updater {
	
	boolean activated;
	
	public Updater(){
		activated = true;
		ListHandler.get().add(this);
		
	}
	
	public abstract void update();
	
	final void callUpdate(){
		if(activated){
			update();
		}
	}
	
	public final void destroy(){
		/**
		 * removes the object from the updater list, thus making it "dead" and possibly removing the only reference to it.
		 * 
		 */
		ListHandler.get().remove(this);
		activated = false;
		onDestroy();
	}
	
	public void onDestroy(){}


}
