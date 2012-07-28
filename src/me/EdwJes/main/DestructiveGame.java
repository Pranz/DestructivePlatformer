package me.EdwJes.main;

import java.util.List;

import me.EdwJes.main.objects.RenderableObject;
import me.EdwJes.main.objects.Entities.Entity;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class DestructiveGame implements Game {
	
	static public Input input;
	private Entity testEntity;

	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return Main.TITLE;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		input = container.getInput();
		testEntity=new Entity(40, Main.WINDOW_HEIGHT/2);


	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for(RenderableObject object: RenderableObject.list){
			object.render(g);
		}
		
		g.drawString("Vspeed: "+testEntity.vspeed,0,0);

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		List<Updater> tempList = Updater.list;
		for(Updater object: tempList){
			object.callUpdate();
		}
	}
	
}
