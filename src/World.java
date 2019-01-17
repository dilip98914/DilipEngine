import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

// sample path for save and load "E:\\dilipEngineFiles\\abc.txt"
public class World {
	public static int width = 50, height = 50;// 30x30 is safe below which
												// system crashes out of array
												// exception
	private int xStart, xEnd, yStart, yEnd;
	public static int[][] bufferWorld;
	public static int[][] bufferEnvironment;
	
	
	private Handler handler;
	private Formatter formatter;
	private Scanner scanner;

	private Font awtFont;
	private TrueTypeFont font;
	private boolean saved = false, loaded = false;
	public Texture rainTex;
	double rainY2 = 0, rainY = 0;
	boolean rainning = false;
	public boolean defaults = false;

	public World(Handler handler) {
		this.handler = handler;
		awtFont = new Font("Verdana", Font.PLAIN, 18);
		font = new TrueTypeFont(awtFont, false);
		bufferWorld = new int[height][width];
		bufferEnvironment=new int[height][width];//default id filled 100

		try {
			rainTex = TextureLoader.getTexture("PNG", new FileInputStream(
					"res/rain.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		intialEnvironment();
		
	}
	
	
	public static void intialEnvironment(){
		for(int j=0;j<height;j++){
			for(int i=0;i<width;i++){
				bufferEnvironment[j][i]=100;
			}
		}
	}

	
	private void rainRendering() {
		// for 480p
		Renderer.renderImage(rainTex, 0, (float) rainY - 32, 640, 512,
				new float[] { 0, 0, 1, 0, 1, 1, 0, 1 }, 1);// yOffset=32;
		rainY += 1;
		if (rainY > 31)
			rainY = 0;
		// Renderer.renderImage(rainTex, 0,0, 512,512,new
		// float[]{0,0,1,0,1,1,0,1});G
		// Game.printer(Double.toString(rainY));

	}

	public void update() {// check exiting out of the world when changing world
							// size of all entities
		handler.getLevelEditor().editing();

		if (defaults) {
			loadInfo("E:\\dilipEngineFiles\\InfodefaultSave.txt");
			loadWorld("E:\\dilipEngineFiles\\defaultSave.txt");
			loadEntities("E:\\dilipEngineFiles\\EntitydefaultSave.txt");
			defaults = false;
		} 

		if (CommandPrompt.bufferText.equalsIgnoreCase("/save "
				+ CommandPrompt.saveName)) {
			saveWorld("E:\\dilipEngineFiles\\" + CommandPrompt.saveName);
			saveInfo("E:\\dilipEngineFiles\\" + "Info" + CommandPrompt.saveName);
			saveEntities("E:\\dilipEngineFiles\\" + "Entity"
					+ CommandPrompt.saveName);
			saved = true;
			CommandPrompt.bufferText = "";
		} else {
				//CommandPrompt.bufferText="can't saved check command!";
		}

		if (CommandPrompt.bufferText.equalsIgnoreCase("/load "
				+ CommandPrompt.loadName)) {

			loadInfo("E:\\dilipEngineFiles\\" + "Info" + CommandPrompt.loadName);
			loadWorld("E:\\dilipEngineFiles\\" + CommandPrompt.loadName);
			 loadEntities("E:\\dilipEngineFiles\\"+"Entity"+CommandPrompt.loadName);
			loaded = true;
			CommandPrompt.bufferText = "";

		} else {
			//CommandPrompt.bufferText="can't load check command!";
		}

	}

	public void saveEntities(String text){
		int entityCount=0;
		try {
			formatter=new Formatter(new File(text));
			
		} catch (FileNotFoundException e) {
			Game.printer("saving entities failed!!");
		}
		
		for(Entity e:handler.getEntityManager().entities){
			
			
			for(int i=0;i<CommandPrompt.entities.length;i++){
				if(e.getClass().getSimpleName().equalsIgnoreCase(CommandPrompt.entities[i])){
					
					formatter.format(Integer.toString(i));
					
				}
			}
			
			formatter.format(" ");
			
			formatter.format(Integer.toString((int)e.getX()));
			formatter.format(" ");
			formatter.format(Integer.toString((int)e.getY()));
			formatter.format(" ");
			
			entityCount++;
		}
		
		formatter.format(Integer.toString(entityCount));
		formatter.close();
		
	}

	
	
	
	
	
	
	public void loadEntities(String text) {//////////quiet unefficient but works hahah
		
		
		
		try {
			scanner = new Scanner(new FileInputStream(text));
		} catch (FileNotFoundException e) {
			Game.printer("Info Entitiesfile not loaded!!");

		}
		
		int l=0;
		
		while(scanner.hasNext()){
			l=scanner.nextInt();
		}
		
		scanner.close();
		
		try {
			scanner = new Scanner(new FileInputStream(text));
		} catch (FileNotFoundException e) {
			Game.printer("Info Entitiesfile not loaded!!");

		}
		
		
		
		
		String[] array=new String[l*3+1];
		
		
		for(int i=0;i<array.length;i++){
			array[i]=scanner.next();
			
		}
		
		for(int i=0;i<array.length-1;i+=3){//not for player
			
			//for npc
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="npc"){
				NPC n=new NPC(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),40,40,2,0,handler);
				EntityManager.entities.add(n);
			}
			
		//	for door
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="door"){
				Door d=new Door(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),40,10,0,handler,null);
				EntityManager.entities.add(d);
			}
			
		//for helmet
		
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="helmet"){
				Helmet h=new Helmet(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),16,16,0,handler);
				EntityManager.entities.add(h);
			}
			
			//for apple
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="apple"){
				Apple a=new Apple(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),16,16,0,handler);
				EntityManager.entities.add(a);
			}
			
			//for goldenApple
			
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="goldenApple"){
				GoldenApple gap=new GoldenApple(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),16,16,0,handler);
				EntityManager.entities.add(gap);
			}
			
			//for lab
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="lab"){
				Lab lb=new Lab(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),16*8,16*8,0,handler);
				EntityManager.entities.add(lb);
			}
			
			//for safeHouse
			if(CommandPrompt.entities[Integer.parseInt(array[i])]=="safeHouse"){
				SafeHouse sh=new SafeHouse(Float.parseFloat(array[i+1]),Float.parseFloat(array[i+2]),16*8,16*8,0,handler);
				EntityManager.entities.add(sh);
			}
			
			
	}
		
		scanner.close();

	}
	
	
	
	private void someThing(String str){
		if(str.equalsIgnoreCase("player")){}
			//return new Player();
	}
	
	public void saveInfo(String text) {
		try {
			formatter = new Formatter(new File(text));

		} catch (FileNotFoundException e) {
			Game.printer("saving info failed!!");
		}

		formatter.format(Integer.toString(width));
		formatter.format(" ");
		formatter.format(Integer.toString(height));
		formatter.format(" ");
		formatter.format(Integer.toString((int) handler.getEntityManager()
				.getPlayer().getX()));
		formatter.format(" ");
		formatter.format(Integer.toString((int) handler.getEntityManager()
				.getPlayer().getY()));
		formatter.format(" ");
		formatter.close();

	}

	public void loadInfo(String text) {
		try {
			scanner = new Scanner(new FileInputStream(text));
		} catch (FileNotFoundException e) {
			Game.printer("Info file not loaded!!");

		}

		int[] in = new int[4];
		for (int i = 0; i < in.length; i++) {
			in[i] = Integer.parseInt(scanner.next());
		}

		scanner.close();
		width = in[0];
		height = in[1];
		bufferWorld = new int[height][width];
		int x0 = in[2];
		int y0 = in[3];
		// if(x0/16>=width) x0=(width-3)*16;
		// if(y0/16>=height) y0=(height-3)*16;

		// if(x0/16<=0) x0=(3)*16;
		// if(y0/16<=0) y0=(3)*16;

		handler.getEntityManager().getPlayer().setX(x0);
		handler.getEntityManager().getPlayer().setY(y0);

		// Game.printer(Integer.toString(height));
	}

	public void saveWorld(String text) {
		try {
			formatter = new Formatter(new File(text));

		} catch (FileNotFoundException e) {
			Game.printer("saving world failed!!");
		}

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				formatter.format(Integer.toString(bufferWorld[j][i]));
				formatter.format(" ");
			}
			formatter.format("\n");
		}

		formatter.close();
	}

	public void loadWorld(String text) {
		try {
			scanner = new Scanner(new FileInputStream(text));
		} catch (FileNotFoundException e) {
			Game.printer("file not loaded!!");

		}
		if (new File(text).exists()) {

			for (int j = 0; j < height; j++) {
				for (int i = 0; i < width; i++) {

					bufferWorld[j][i] = Integer.parseInt(scanner.next());

				}
			}

			scanner.close();
		}

	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void defaultWorld() {// if not able to load anything

	}

	public void render() {

		xStart = Math.max(0, (int) (handler.getPlayer().getxOffset() / 16));
		xEnd = Math.min(width,
				(int) (handler.getPlayer().getxOffset() + Game.width) / 16 + 1);

		yStart = Math.max(0, (int) (handler.getPlayer().getyOffset() / 16));
		yEnd = Math
				.min(height,
						(int) (handler.getPlayer().getyOffset() + Game.height) / 16 + 1);

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0, 0, 0, 1);

		for (int j = yStart; j < yEnd; j++) {
			for (int i = xStart; i < xEnd; i++) {
				handler.getGame().getTiles()[bufferWorld[j][i]].render(
						Tile.spriteSheet, i * 16
								- handler.getPlayer().getxOffset(), j * 16
								- handler.getPlayer().getyOffset(), 16, 16, 1);
			}
		}

		// font.drawString(Game.width/2-font.getWidth("RPG Games Engine")/2,
		// Game.height/2,"RPG Games Engine");
		font.drawString(50, 50,
				Integer.toString(handler.getLevelEditor().getMX()) + " "
						+ handler.getLevelEditor().getMY());
		font.drawString(Game.width / 2, Game.height / 2 - 100, "saved: "
				+ saved + " loaded: " + loaded);

		if (CommandPrompt.bufferText.equalsIgnoreCase("start rain")
				&& Mouse.isButtonDown(0))
			rainning = true;

		if (rainning)
			rainRendering();

		if (CommandPrompt.bufferText.equalsIgnoreCase("stop rain")
				&& Mouse.isButtonDown(0))
			rainning = false;

	}

	public int[][] getBufferWorld() {
		return bufferWorld;
	}

	public void setBufferWorld(int[][] bufferWorld) {
		this.bufferWorld = bufferWorld;
	}

}
