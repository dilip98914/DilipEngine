import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Font;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.SoundStore;

public class Game {
	public static int width=640,height=480;
	public static String title="dilipEngine";
	private int fps=60;
	private Sound  sound;
	public static DisplayClass displayClass;
	
	private Tile grassTile,oakWood,stone,sand,water,spruceWood,footpath;
	
	private Tile[] tiles=new Tile[7];//////
	
	private Handler handler;
	private World world;
	private LevelEditor levelEditor;
	private CommandPrompt commandPrompt;
	private EntityManager entityManager;
	private boolean shouldLoop=true;
	private int loopCounter=0;
	
	//all assets
	private Assets assets;
	
	public Game(){
		//printer("hello there!!");
		init();
		run();
	}
	
	
	
	


	private void init(){
		displayClass=new DisplayClass(title,width,height);
		handler=new Handler(this);
		assets=new Assets(handler);

		Renderer.init();
		grassTile=new Tile(new float[]{0,0,0.125f,0,0.125f,0.125f,0,0.125f},false,0);
		stone=new Tile(new float[]{7f/8f,0,1f,0,1f,1f/8f,7f/8f,1f/8f},true,1);
		oakWood=new Tile(new float[]{7f/8f-1f/8f,0,1f-1f/8f,0,1f-1f/8f,1f/8f,7f/8f-1f/8f,1f/8f},false,2);
		sand=new Tile(new float[]{1f/8f,0,2f/8f,0,2f/8f,1f/8f,1f/8f,1f/8f},false,3);
		water=new Tile(new float[]{4f/8f,0,5f/8f,0,5f/8f,1f/8f,4f/8f,1f/8f},false,4);
		spruceWood=new Tile(new float[]{2f/8f,1f/8f,3f/8f,1f/8f,3f/8f,2f/8f,2f/8f,2f/8f},false,5);
		//__tex=new Tile(new float[]{0,0,1,0,1,1,0,1},false,6);
		footpath=new Tile(new float[]{3f/8f,0,4f/8f,0,4f/8f,1f/8f,3f/8f,1f/8f},false,6);
		
		
		//very bad hardcode
		tiles[grassTile.getId()]=grassTile;
		tiles[stone.getId()]=stone;
		tiles[oakWood.getId()]=oakWood;
		tiles[sand.getId()]=sand;
		tiles[water.getId()]=water;
		tiles[spruceWood.getId()]=spruceWood;
		tiles[footpath.getId()]=footpath;
		
		world=new World(handler);
		levelEditor=new LevelEditor(handler);
		commandPrompt=new CommandPrompt(handler);
		
		
		entityManager=new EntityManager(handler);
		Sound.init();
	}
	

	public void run(){
		
		long lastTime=System.nanoTime();
		long now=0;
		double delta=0;
		double nsPerTick=1000000000/fps;
		long timer=0;
		int frames=0,updates=0;
		
		while(!Display.isCloseRequested()){
			now=System.nanoTime();
			delta+=(now-lastTime)/nsPerTick;
			timer+=now-lastTime;
			lastTime=now;
			
			if(delta>=1){
				update();
				updates++;
				delta--;
				
			}
			
			render();
			frames++;
			
			if(timer>=1000000000){
				Display.setTitle(title+"  "+"frames: "+frames+" "+"updates: "+updates);
				timer=0;
				frames=0;
				updates=0;
			}
			Display.update();
			
			
		}
		destroyAll();
	}

	
	private void update(){
		EventManager.checkKeys();
		
		world.update();
		entityManager.update();
		commandPrompt.update();
		
		if( !Sound.defaultBackgroundMusic.isPlaying()){
			
		
			Sound.play(Sound.defaultBackgroundMusic,0.7f,1f,true);//23 seconds
		//	if(loopCounter>=60*23) loopCounter=0;
			
		}
		
		//loopCounter++;
	}
	private void render(){

		world.render();
		commandPrompt.render();
		
		entityManager.render();
		
		
		

	}
	
	






	public static void destroyAll(){
		Renderer.destroy();
		
		Display.destroy();
		AL.destroy();
		printer("display is destroyed");
		System.exit(0);
	}
	
	public static void printer(String text){
		System.out.println(text);
	}
	
	//getters and setters
	
	public Handler getHandler() {
		return handler;
	}




	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public Tile getGrassTile() {
		return grassTile;
	}
	public void setGrassTile(Tile grassTile) {
		this.grassTile = grassTile;
	}
	public Tile getOakWood() {
		return oakWood;
	}
	public void setOakWood(Tile oakWood) {
			this.oakWood = oakWood;
		}
	public Tile getStone() {
			return stone;
		}
	public void setStone(Tile stone) {
			this.stone = stone;
		}
	public Tile getSand() {
			return sand;
		}
	public void setSand(Tile sand) {
			this.sand = sand;
		}
	public Tile getWater() {
			return water;
		}
	public void setWater(Tile water) {
			this.water = water;
		}
	public Tile getSpruceWood() {
			return spruceWood;
		}
	public void setSpruceWood(Tile spruceWood) {
			this.spruceWood = spruceWood;
		}
	
	



	public Assets getAssets() {
		return assets;
	}




	public World getWorld() {
			return world;
		}
	public void setWorld(World world) {
		this.world = world;
	}


	public LevelEditor getLevelEditor() {
		return levelEditor;
	}






	public EntityManager getEntityManager() {
		return entityManager;
	}






	public void setLevelEditor(LevelEditor levelEditor) {
		this.levelEditor = levelEditor;
	}






	public Tile getFootpath() {
		return footpath;
	}






	public CommandPrompt getCommandPrompt() {
		return commandPrompt;
	}






	public Tile[] getTiles() {
		return tiles;
	}
	
	
}
