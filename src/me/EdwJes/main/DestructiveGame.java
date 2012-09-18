package me.EdwJes.main;

import java.util.ArrayList;
import java.util.List;


import me.EdwJes.main.input.PlayerInput;
import me.EdwJes.main.objects.RenderableObject;
import me.EdwJes.main.objects.block.Block;
import me.EdwJes.main.objects.entities.Entity;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class DestructiveGame implements Game {
	
	Entity testEntity;
	public static PlayerInput player;
	public static Console cmd;

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
		new Block(32, Main.WINDOW_HEIGHT - 64, 7, 4); new Block(32*5, Main.WINDOW_HEIGHT - 80, 22, 4);
		cmd = new Console();


	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.translate(Main.view.x, Main.view.y);
		for(RenderableObject object: ListHandler.get().getList(ListHandler.RENDERABLEOBJECT, RenderableObject.class)){
			object.render(g);
		}
		List<String> stringList = new ArrayList<String>();
		
		g.translate(-Main.view.x, -Main.view.y);
		stringList.add("FPS: " + Main.getContainer().getFPS());
		stringList.add("hspeed: " + testEntity.hspeed);
		stringList.add("vspeed: " + testEntity.vspeed);
		stringList.add("test1: "  + (testEntity.prevX - testEntity.x));
		stringList.add("test2: "  + (testEntity.prevY - testEntity.y));
		stringList.add("x: "      + testEntity.x);
		stringList.add("y: "      + testEntity.y);
		stringList.add("speedMod: "    + Entity.sumOfList(testEntity.speedMods));
		
		drawList(g, stringList, 0, 0);

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		List<Updater> tempList = ListHandler.get().getList(ListHandler.UPDATER);
		for(int i = 0; i < tempList.size(); i ++){
			tempList.get(i).update();
		}
	}
	
	public void drawList(Graphics g, List<String> stringList, int x, int y){
		for(int i = 0; i < stringList.size(); i++){
			g.drawString(stringList.get(i), x, y + 13 * i);
		}
	}
	
}
