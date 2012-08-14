package me.EdwJes.main.objects.entities.action;

import me.EdwJes.main.objects.entities.Entity;

public class MeleeAttack extends InstantAction {
	

	public MeleeAttack(Entity ent) {
		super(ent);
		delay = 15;
		afterLag = 10;
		speedModifier = -0.45;
	}
	
	@Override
	public void preAction() {
		ent.addSpeedBuff(speedModifier);

	}

	@Override
	public void action() {

	}

	@Override
	public void resolve() {
		ent.removeSpeedBuff(speedModifier);
	}

	@Override
	public boolean conditionsAreMet() {
		return true;
	}

}
