import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class LevelEditor {
	private Handler handler;
	private int MX, MY;
	private boolean editingMode = false;
	private int currentTileId = 0,currentEntityId=0;
	private boolean entityEditingMode = false;
	private boolean added=false;
	private boolean onceDone=false;
	
	public LevelEditor(Handler handler) {
		this.handler = handler;
	}

	public void editTile() {
		
		if (EventManager.tKey == Keyboard.KEY_C) {
			currentTileId++;

			if (currentTileId >= handler.getTiles().length)
				currentTileId = 0;
		}

		if (editingMode) {
			if (Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width
					&& MY < World.height) {
				World.bufferWorld[MY][MX] = currentTileId;
			}
		
		}
		

	}

	public void editEntity() {
		
		
		if (EventManager.tKey == Keyboard.KEY_C) {
			currentEntityId++;

			if (currentEntityId >= CommandPrompt.entities.length)
				currentEntityId = 0;
		}

		if (entityEditingMode) {
			System.out.println("hello there");
			//npc
			if (currentEntityId == 1 && Mouse.isButtonDown(0) && MX > 0 && MY > 0
					&& MX < World.width && MY < World.height && currentEntityId != 0 && !added) {
				EntityManager.entities.add(new NPC(MX * 16, MY * 16, 40, 40, 2,
						0, handler));
				added=true;
			}
			
			if(Mouse.isButtonDown(1)) added=false;
			//doors always in pair
			if (currentEntityId == 2 && Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width && MY < World.height && currentEntityId != 0 && !added){
				Door d1=new Door(MX*16,MY*16,40,10,0,handler,null);
				//Door d2=new Door((MX+4)*16,(MY+4)*16,40,10,0,handler,null);//pure temporary
				EntityManager.entities.add(d1);
				//EntityManager.entities.add(d2);
				
				if(!onceDone){
					//d1.setDestDoor(d2);
					//d2.setDestDoor(d1);
					onceDone=true;
				}
			
				
				added=true;

			}
			
			//helmet
			if (currentEntityId == 3 && Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width && MY < World.height && currentEntityId != 0 && !added){
				
				EntityManager.entities.add(new Helmet(MX * 16, MY * 16, 16, 16, 0, handler));
				added=true;
				
			}
			
			//apple
			if (currentEntityId == 4 && Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width && MY < World.height && currentEntityId != 0 && !added){
							
							EntityManager.entities.add(new Apple(MX * 16, MY * 16, 16, 16, 0, handler));
							added=true;
							
			}
			
			//goldenApple
			if (currentEntityId == 5 && Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width && MY < World.height && currentEntityId != 0 &&!added){
							
							EntityManager.entities.add(new GoldenApple(MX * 16, MY * 16, 16, 16, 0, handler));
							added=true;
							
			}
			
			
			//lab
			if (currentEntityId == 6 && Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width && MY < World.height && currentEntityId != 0 &&!added){
							
							EntityManager.entities.add(new Lab(MX * 16, MY * 16, 16*8, 16*8, 0, handler));
							added=true;
							
			}
			
			//safeHouse
			if (currentEntityId == 7 && Mouse.isButtonDown(0) && MX > 0 && MY > 0 && MX < World.width && MY < World.height && currentEntityId != 0 &&!added){
							
							EntityManager.entities.add(new SafeHouse(MX * 16, MY * 16, 16*8, 16*8, 0, handler));
							added=true;
							
			}
			
			
		}
	}

	public void editing() {

		MX = (int) (Mouse.getX() + handler.getPlayer().getxOffset()) / 16;
		MY = (int) (Game.height - Mouse.getY() + handler.getPlayer()
				.getyOffset()) / 16;

		
		

		editTile();
		editEntity();
	}

	public int readTile() {
		Tile t = handler.getGame().getTiles()[handler.getBufferWorld()[MY][MX]];
		if (MX > 0 && MY > 0 && MX < World.width && MY < World.height) {
			return t.getId();
		} else {
			return 100;
		}
	}

	public int getMX() {
		return MX;
	}

	public void setMX(int mX) {
		MX = mX;
	}

	public int getMY() {
		return MY;
	}

	public void setMY(int mY) {
		MY = mY;
	}

	public boolean isEditingMode() {
		return editingMode;
	}

	public void setEditingMode(boolean editingMode) {
		this.editingMode = editingMode;
	}

	

	public int getCurrentTileId() {
		return currentTileId;
	}

	public void setCurrentTileId(int currentTileId) {
		this.currentTileId = currentTileId;
	}

	public int getCurrentEntityId() {
		return currentEntityId;
	}

	public void setCurrentEntityId(int currentEntityId) {
		this.currentEntityId = currentEntityId;
	}

	public boolean isEntityEditingMode() {
		return entityEditingMode;
	}

	public void setEntityEditingMode(boolean entityEditingMode) {
		this.entityEditingMode = entityEditingMode;
	}
	

}
