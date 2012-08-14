package me.EdwJes.main.objects.entities.action;

import me.EdwJes.main.objects.entities.Entity;

public abstract class InstantAction extends Action {

	public InstantAction(Entity ent) {
		super(ent);
	}

	
	@Override
	public void onKeyPress() {
		executeAction();
	}

	@Override
	public void onKeyDown() {}

	@Override
	public void onKeyRelease() {}

}
