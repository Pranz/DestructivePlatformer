package me.EdwJes.main.objects.entities.action;

import me.EdwJes.main.objects.entities.Entity;

public abstract class ChargingAction extends Action {
	
	public int charge, maxCharge;

	public ChargingAction(Entity ent) {
		super(ent);
		charge = 0;
	}

	@Override
	public void onKeyDown() {
		if(charge < maxCharge)charge++;
	}
}
