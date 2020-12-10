package fr.vyxs.routes.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.google.gson.Gson;

import fr.vyxs.routes.controller.PlayerController;
import fr.vyxs.routes.debug.Log;
import fr.vyxs.routes.entity.Player;
import fr.vyxs.routes.graphics.ImageBlockType;
import fr.vyxs.routes.graphics.MouseCursor;
import fr.vyxs.routes.graphics.Grid;
import fr.vyxs.routes.math.SideAllowed;
import fr.vyxs.routes.model.BlockModel;

public class Game extends BasicGame {
	
	private final AppGameContainer appGameContainer;

	private World world;
	private Grid grid;
	
	private App app;
	
	private Player localPlayer;
	private PlayerController playerController;
	private MouseCursor cursor;
	
	public Game(App app) {
		super(app.getAppName());
		this.app = app;
		appGameContainer = app.getAppGameContainer();
		
		Log.test();
		//Type type = new TypeToken()
		App.log("hihi" + new Gson().toJson(new BlockModel(ImageBlockType.RAIL_RIGHT_BOT_LEFT, new SideAllowed(true, true, false, true))).toString());
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		app.onInit();
		world = app.getMainWorld();
		localPlayer = app.getLocalPlayer();
		grid = app.getDebugGrid();
		cursor = app.getMouseCursor();
		playerController = app.getPlayerController();
		
		world.prepopulate();
	}

	@Override
	public void update(GameContainer gameContainer, int deltaTime) throws SlickException {
		world.update(gameContainer, deltaTime);
		cursor.update(gameContainer, deltaTime);
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		world.render(gameContainer, graphics);
		grid.render(gameContainer, graphics);
		
		localPlayer.render(gameContainer, graphics);
		
		cursor.render(gameContainer, graphics);
		cursor.renderDebug(graphics);
	}
	
	@Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
        	appGameContainer.exit();
        }
    }
}
