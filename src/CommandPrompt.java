import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

public class CommandPrompt {
	private Handler handler;
	public static String bufferText = "";
	private boolean tilePlacing = false;
	private boolean entityPlacing = false;
	private boolean showPrompt = true;

	private boolean tileState = false, entityState = false,
			normalState = true;

	private String[] states = { "tileState", "entityState", "normalState" };

	public static String[] commands = { "/save", "/load", "/close", "/exit",
			"/tile", "/entity", "/give", "/enter", "/setSize","/setById","/setByName","/getTile","/givec" };
	public static String[] entities = { "player", "npc","door","helmet", "apple",
			"goldenApple","lab","safeHouse"  };
	public static String[] tiles = { "grassTile", "stone", "oakWood", "sand",
			"water", "spruceWood", "footpath" };//same sequence as game.tiles

	public static String saveName, loadName;// = "abc.txt", loadName =
											// "abc.txt";
	public static boolean nameComplete = false;
	public static boolean setSize = false;
	private int x = 2, y = Game.height - 18 - 2, width = 0, height = 0;
	private boolean focus = true;
	private boolean clicked = false;
	private boolean saving = false, loading = false;

	private GUI gui;
	private int defaultIndex = 0;

	boolean bool,bool1,bool2,bool3,bool4,bool5 = false;

	public CommandPrompt(Handler handler) {
		this.handler = handler;
		gui = new GUI();
		gui.init(18);

	}

	private void addingToBufferText() {
		if (EventManager.character != "" && showPrompt
				&& (!Keyboard.isKeyDown(Keyboard.KEY_BACK))
				&& (!Keyboard.isKeyDown(Keyboard.KEY_RETURN)) && focus) {
			bufferText += EventManager.character;
		}
	}

	private void deletingFromBufferText() {
		if (EventManager.tKey == Keyboard.KEY_BACK && bufferText.length() > 0
				&& showPrompt && focus) {
			bufferText = bufferText.substring(0, bufferText.length() - 1);
		}
	}

	private void commandReading() {
		if (bufferText != "" && focus)
			commandReader2();

		// if(EventManager.tKey!=Keyboard.KEY_RETURN &&
		// EventManager.tKey!=Keyboard.KEY_SPACE)
		defaultIndex = 11 * (bufferText.length() - 1);
	}

	private void focusUpdate() {
		width = gui.getFont().getWidth(bufferText);
		// System.out.println(width+7);
		height = gui.getFont().getHeight(bufferText);

		if (Mouse.getX() >= x && Mouse.getX() < x + width + 100
				&& Mouse.getY() >= 0 && Mouse.getY() <= 26
				&& Mouse.isButtonDown(0)) {
			clicked = true;

		}

		if (Mouse.isButtonDown(1)) {
			clicked = false;
		}

		if (clicked) {
			focus = true;
		} else {
			focus = false;
		}
		// System.out.println("focus: " + focus);
	}

	public void commandReader2() {
		changeState();
		if (normalState) {

			savingAndLoadingCommand();
			exitCommand();
			setWorldSizeCommand();
		}

		if (tileState) {
			handler.getLevelEditor().setEditingMode(true);
			tileCommand();
		} else {
			handler.getLevelEditor().setEditingMode(false);

		}

		if (entityState) {
			handler.getLevelEditor().setEntityEditingMode(true);
			entityCommand();
		}else{
			handler.getLevelEditor().setEntityEditingMode(false);

		}
	}

	// commands for tileState
	private String idToTile(int id,String[] array) {// type id and it will give tileName
		return array[id];

	}

	private int currentTileId() {// gives current id
		return handler.getLevelEditor().getCurrentTileId();
	}
	
	private int tileStringToId(String str,String[] array){//default return 100 remember//////////
		int index=100;
		for(int i=0;i<array.length;i++){
			if(array[i].equalsIgnoreCase(str)){
				index=i;
			}
		}
		
		return index;
	}

	private int getTileToId() {
		return handler.getLevelEditor().readTile();//gives 100 precuation  of it
	}

	// ////////////////////////////////
	private void tileCommand() {
		
		
	
		
		////get tileName accoring to id written
		if (bufferText.equalsIgnoreCase(commands[6])) {
			bool=true;
		}
		
		
		//if somebody mistakenly write command that not want to execute then he must delete command completely first
		if(bufferText.equalsIgnoreCase("") ){
			bool=false;
		}
		
		if(bool && EventManager.tKey== Keyboard.KEY_RETURN && bufferText.length()==7){
			if(Integer.parseInt(bufferText.substring(6))<tiles.length)
				bufferText=idToTile(Integer.parseInt(bufferText.substring(6)),tiles);//later use concat
			else{
				bufferText="id passed doesn't exists!";
			}
			bool=false;
			//bufferText="";
		}
		
		
		
	////get id accoring to tileName written
			if (bufferText.equalsIgnoreCase(commands[6]) ) {
				bool=true;
			}
			
			
			//if somebody mistakenly write command that not want to execute then he must delete command completely first
			if(bufferText.equalsIgnoreCase("") || bufferText.equalsIgnoreCase(commands[12]) ){
				bool=false;
			}
			
			if(bool && EventManager.tKey== Keyboard.KEY_RETURN){
				int i=tileStringToId(bufferText.substring(6, bufferText.length()),tiles);
				if(i!=100) {
					bufferText=Integer.toString(i);
				}else{
					bufferText="name passed doesn't exists!";
				}
				bool=false;
				//bufferText="";
			}
		
		
		
		//set currentId = id passed
		if (bufferText.equalsIgnoreCase(commands[9])) {
			bool1=true;
		}
		
		if(bufferText.equalsIgnoreCase("") ){
			bool1=false;
		}
		
		
		if(bool1 && EventManager.tKey== Keyboard.KEY_RETURN){
			handler.getLevelEditor().setCurrentTileId(Integer.parseInt(bufferText.substring(9)));//later put precautions
			bool1=false;
			bufferText="";
		}
		
		//set currentid to tilename passed
		
		if (bufferText.equalsIgnoreCase(commands[10])) {
			bool2=true;
		}
		
		if(bufferText.equalsIgnoreCase("")){
			bool2=false;
		}
		
		
		if(bool2 && EventManager.tKey== Keyboard.KEY_RETURN){
			
			handler.getLevelEditor().setCurrentTileId(tileStringToId(bufferText.substring(11, bufferText.length()),tiles));//later put precautions i,e if string not exists
			bool2=false;
			bufferText="";

		}
		
		//read tile id and set current id to that id
		
		if (bufferText.equalsIgnoreCase(commands[11])) {
			bool3=true;
		}
		
		if(bufferText.equalsIgnoreCase("") ){
			bool3=false;
		}
		
		
		
		if(bool3 && EventManager.tKey==Keyboard.KEY_RETURN){
			bool4=true;
			if(bool4){
			getTileToId();//////////////////////////////////////////get this
			
			bool3=false;
			bool4=false;
			}
		}
		
		
		

		// for current ///why this returning wrong values
		if (bufferText.equalsIgnoreCase(commands[12] + " currentId")
				&& EventManager.tKey == Keyboard.KEY_RETURN) {
			bufferText = Integer.toString(currentTileId());

		}

		if (bufferText.equalsIgnoreCase(commands[12] + " currentTile")
				&& EventManager.tKey == Keyboard.KEY_RETURN) {
			bufferText = idToTile(currentTileId(),tiles);

		}

		
	}
	
	
	private void entityCommand(){
		////get entity accoring to id written
		if (bufferText.equalsIgnoreCase(commands[6])) {
			bool=true;
		}
		
		
		//if somebody mistakenly write command that not want to execute then he must delete command completely first
		if(bufferText=="" ){
			bool=false;
		}
		
		if(bool && EventManager.tKey== Keyboard.KEY_RETURN && bufferText.length()==7){
			if(Integer.parseInt(bufferText.substring(6))<entities.length)
				bufferText=idToTile(Integer.parseInt(bufferText.substring(6)),entities);//later use concat
			else{
				bufferText="id passed doesn't exists!";
			}
			bool=false;
			//bufferText="";
		}
		
		
		
	////get id accoring to entity written
			if (bufferText.equalsIgnoreCase(commands[6]) ) {
				bool=true;
			}
			
			
			//if somebody mistakenly write command that not want to execute then he must delete command completely first
			if(bufferText=="" ){
				bool=false;
			}
			
			if(bool && EventManager.tKey== Keyboard.KEY_RETURN){
				int i=tileStringToId(bufferText.substring(6, bufferText.length()),entities);
				if(i!=100) {
					bufferText=Integer.toString(i);
				}else{
					bufferText="name passed doesn't exists!";
				}
				bool=false;
				//bufferText="";
			}
		
	
	}
	

	public void update() {
		focusUpdate();

		addingToBufferText();
		deletingFromBufferText();
		commandReading();
	}

	private void changeState() {
		if (bufferText.equalsIgnoreCase("/enter " + states[0])
				&& EventManager.tKey == Keyboard.KEY_RETURN) {// tileState
			tileState = true;
			entityState = normalState = false;
			bufferText = "";

		}

		if (bufferText.equalsIgnoreCase("/enter " + states[1])
				&& EventManager.tKey == Keyboard.KEY_RETURN) {// entityState
			entityState = true;
			tileState = normalState = false;
			bufferText = "";

		}

		if (bufferText.equalsIgnoreCase("/enter " + states[2])
				&& EventManager.tKey == Keyboard.KEY_RETURN) {// nomralState
			normalState = true;
			entityState = tileState = false;
			bufferText = "";

		}

	}

	private void savingAndLoadingCommand() {

		if (bufferText.length() >= 5) {

			if (bufferText.substring(0, 5).equalsIgnoreCase(commands[0])) {
				saving = true;
				System.out.println("oksadfbkdbfkdobkdls");
			}
		}

		if (saving) {
			if (EventManager.tKey == Keyboard.KEY_RETURN) {
				nameComplete = true;
				System.out.println("nameComplete: " + nameComplete);

			}

			if (nameComplete) {

				saveName = bufferText.substring(6, bufferText.length());
				System.out.println(saveName);

				saving = false;
				nameComplete = false;

			}

		}
		if (bufferText.length() >= 5) {

			if (bufferText.substring(0, 5).equalsIgnoreCase(commands[1])) {
				loading = true;
				System.out.println("oksls");
			}
		}

		if (loading) {
			if (EventManager.tKey == Keyboard.KEY_RETURN) {
				nameComplete = true;
			}

			if (nameComplete) {

				loadName = bufferText.substring(6, bufferText.length());
				System.out.println(loadName);
				loading = false;
				nameComplete = false;

			}

		}
	}

	private void exitCommand() {// similiarly other commands
		if (bufferText.equalsIgnoreCase(commands[2])
				|| bufferText.equalsIgnoreCase(commands[3])) {
			if (EventManager.tKey == Keyboard.KEY_RETURN) {
				Game.destroyAll();

			}
		}

	}

	private void setWorldSizeCommand() {
		if (bufferText.equalsIgnoreCase(commands[8]))
			setSize = true;

		if (setSize) {
			if (EventManager.tKey == Keyboard.KEY_RETURN) {// 3 digits later
															// smart technology
				World.width = Integer.parseInt(bufferText.substring(9, 12));
				World.height = Integer.parseInt(bufferText.substring(13, 16));
				World.bufferWorld = new int[World.height][World.width];
				bufferText = "";
				setSize = false;

			}

		}
	}

	public void render() {
		// Game.printer("render command");

		if (showPrompt) {
			// gui.textArea(2, Game.height-18-2, Game.width-50, 18, new
			// float[]{1.0f,1.0f,1.0f,0.5f},bufferText);
			gui.variableTextArea(x, y, new float[] { 1f, 1f, 1f, 0.5f },
					Color.black, bufferText);
			Renderer.renderRect(new float[] { 0, 0, 0, 1 }, defaultIndex,
					y + 4, 14, 14);

			// gui.variableTextArea(2, Game.height-18-2,new
			// float[]{1f,1f,1f,0.5f},Color.red, bufferText);
			// this lines makes wqhole background to that color wowwww
		}
	}

	private void pointer() {
		defaultIndex = bufferText.length() - 1;

	}

	public void StringFinder(String text, String argument) {
		
	}

}
