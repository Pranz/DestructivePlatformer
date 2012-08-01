package me.EdwJes.main;

import java.util.List;

import me.EdwJes.main.input.PlayerInput;
import me.EdwJes.main.objects.RenderableObject;
import me.EdwJes.main.objects.Block.Block;
import me.EdwJes.main.objects.Entities.Entity;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DestructiveGame implements Game {
	
	private Entity testEntity;
	PlayerInput player;

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
		Main.view = new View();
		Main.input = container.getInput();
		testEntity = new Entity(40, Main.WINDOW_HEIGHT/2);
		player = new PlayerInput(testEntity);
		Main.view.followPlayer(player);
		new Block(32, Main.WINDOW_HEIGHT - 64, 4, 4);


	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for(RenderableObject object: RenderableObject.list){
			object.render(g, PlayerInput.getPlayer(0).view);
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
