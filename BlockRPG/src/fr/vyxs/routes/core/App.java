package fr.vyxs.routes.core;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import fr.vyxs.routes.controller.PlayerController;
import fr.vyxs.routes.debug.Log;
import fr.vyxs.routes.entity.Player;
import fr.vyxs.routes.entity.UniqueIdentifier;
import fr.vyxs.routes.exception.NoModelFound;
import fr.vyxs.routes.graphics.BlockImageList;
import fr.vyxs.routes.graphics.Grid;
import fr.vyxs.routes.graphics.ImageBlockType;
import fr.vyxs.routes.graphics.MouseCursor;
import fr.vyxs.routes.math.EntityRotationTransformer;
import fr.vyxs.routes.math.Integer2D;
import fr.vyxs.routes.misc.Constants;

public class App {
	
	private AppGameContainer appGameContainer;
	private static final String APP_NAME = "RailPG";
	private static final BlockImageList APP_BLOCK_IMAGE_LIST = new BlockImageList(64);
	private World world;
	private Grid grid;
	
	private UniqueIdentifier localPlayerID;
	private Player localPlayer;
	private MouseCursor cursor;
	private PlayerController playerController;
	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 720;
	
	public static void main(String[] args) {
		setLWJGLnativePath();
		App app = new App();
	}
	
	public static final void setLWJGLnativePath() {
		System.setProperty("org.lwjgl.librarypath", new File("./lib/native/").getAbsolutePath());
	}
	
	public App() {
		try {
			preload();
			launch();
		} catch (SlickException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void onInit() {
		try {
			APP_BLOCK_IMAGE_LIST.load();
		} catch (SlickException x) {
			App.log(x.getMessage());
		}
		
		world 		= new World(APP_BLOCK_IMAGE_LIST);
		localPlayer.setWorldInteraction(world);
		
		localPlayerID = world.register(localPlayer);
		world.setCustomRender(localPlayerID, true);
		
		try {
			cursor = new MouseCursor(APP_BLOCK_IMAGE_LIST);
		} catch (NoModelFound x) {
			App.log(x.getMessage());
		}
		
		playerController.setMouseCursor(cursor);
		localPlayer.setImage(APP_BLOCK_IMAGE_LIST.get(ImageBlockType.PLAYER));
	}
	
	public AppGameContainer getAppGameContainer() {
		return appGameContainer;
	}
	
	public BlockImageList getDefaultBlockImageList() {
		return APP_BLOCK_IMAGE_LIST;
	}
	
	public World getMainWorld() {
		return world;
	}
	
	public Grid getDebugGrid() {
		return grid;
	}
	
	public UniqueIdentifier getLocalPlayerID() {
		return localPlayerID;
	}
	
	public Player getLocalPlayer() {
		return localPlayer;
	}
	
	public MouseCursor getMouseCursor() {
		return cursor;
	}
	
	public PlayerController getPlayerController() {
		return playerController;
	}
	
	public String getAppName() {
		return APP_NAME;
	}
	
	protected void launch() throws SlickException {
		appGameContainer = new AppGameContainer(new Game(this));
		appGameContainer.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		appGameContainer.setTargetFrameRate(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate());
		appGameContainer.setTitle("i");
		appGameContainer.start();
	}
	
	protected void preload() {
		
		
		log("normalize(12.781f) -> " + EntityRotationTransformer.normalize(12.781f));
		log("normalize(42.354f) -> " + EntityRotationTransformer.normalize(42.354f));
		log("normalize(92.621f) -> " + EntityRotationTransformer.normalize(92.621f));
		log("normalize(179.954f) -> " + EntityRotationTransformer.normalize(179.954f));
		log("normalize(299.379f) -> " + EntityRotationTransformer.normalize(299.379f));
		log("normalize(359.104f) -> " + EntityRotationTransformer.normalize(359.104f));
		
		
		
		localPlayer = new Player(new Integer2D(5, 5));
		grid 		= new Grid(Constants.REAL_BLOCK_SIZE);
		playerController = new PlayerController();
		playerController.bindPlayer(localPlayer);
	}
	
	public static void log(String name, Level level, Object... objects) {
		StringBuilder line = new StringBuilder();
		for (Object o : objects)
			line.append(o.toString());
		Logger.getLogger(name).log(level, line.toString());
		System.out.println(String.format("<%s>[%s] %s", level.getName(), name, line.toString()));
	}
	
	public static void log(Object o) {
		log(App.class.getSimpleName(), Level.FINE, o);
	}
	
	public static void logIfNull(Object o, String message) {
		if (o == null)
			log(message);
	}

}
