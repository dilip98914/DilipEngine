import org.lwjgl.util.Rectangle;

public class Door extends NonCollectable {
	private float destX, destY;
	private float currX, currY;
	private boolean upperCollided = false, lowerCollided = false;
	private boolean done = false;
	private Door destDoor;// intialized by getter and setters
	private String text;
	private boolean loaded=false;
	
	
	public Door(float x, float y, float width, float height, float health,
			Handler handler,String text) {
		super(x, y, width, height, health, handler);
		hitBox.setX(0);
		hitBox.setY(0);
		hitBox.setWidth((int) width);
		hitBox.setHeight((int) height);

		currX = x;

		currY = y + height + 6;
		destX=0;
		destY=0;
/*		if (destDoor != null)
			destX = destDoor.getCurrX();
		else {
			destX = 0;
			System.err.println("destX not initialized");
		}
		if (destDoor != null)
			destY = destDoor.getCurrY();
		else {
			
			destY = 0;
			System.err.println("destY not initialized");
*/		
		if(text!=null)
			this.text=text;
	}

	public void update() {
		if(handler.getPlayer().getX()+13>x+2 && handler.getPlayer().getX()+13+handler.getPlayer().hitBox.getWidth()<x+width-2 )
			checkCollision();
		if ((upperCollided || lowerCollided)/*
											 * && CommandPrompt.bufferText.
											 * equalsIgnoreCase("door")
											 */) {
			
			if(!loaded){
				
			
			//handler.getWorld().loadInfo("E:\\dilipEngineFiles\\" + "Info" + text);
			//handler.getWorld().loadWorld("E:\\dilipEngineFiles\\" + text);
			//handler.getWorld().loadEntities("E:\\dilipEngineFiles\\"+"Entity"+text);
			loaded=true;
			}
			
			handler.getPlayer().setX(destX);
			handler.getPlayer().setY(destY);
			CommandPrompt.bufferText = "";
			upperCollided = lowerCollided = false;
		}
	}

	private void checkCollision() {
		if (checkEntityCollision(0, 5)) {
			lowerCollided = true;
			//System.out.println("hi");
			if (destDoor != null) {
				//System.out.println("heloooooo!!!!");
				destDoor.setCurrY(destDoor.getY()-height- 32);
				destY = destDoor.getCurrY();
				destX=destDoor.getCurrX();
			}

		} else
			lowerCollided = false;
		if (checkEntityCollision(0, -5)) {
			upperCollided = true;
			if (destDoor != null) {
				destDoor.setCurrY(destDoor.getY() + height + 6);
				destY = destDoor.getCurrY();
				destX=destDoor.getCurrX();

			}
		} else
			upperCollided = false;
	}

	public void render() {
		Renderer.renderQuad(new float[] { 0, 0, 0, 1 },
				x - handler.getXOffset(), y - handler.getYOffset(), width,
				height);
		Renderer.renderRect(new float[] { 0.75f, 0.75f, 0.75f, 1 },
				x - handler.getXOffset(), y - handler.getYOffset(), width,
				height);

	}

	public float getDestX() {
		return destX;
	}

	public void setDestX(float destX) {
		this.destX = destX;
	}

	public float getDestY() {
		return destY;
	}

	public void setDestY(float destY) {
		this.destY = destY;
	}

	public boolean isUpperCollided() {
		return upperCollided;
	}

	public void setUpperCollided(boolean upperCollided) {
		this.upperCollided = upperCollided;
	}

	public boolean isLowerCollided() {
		return lowerCollided;
	}

	public void setLowerCollided(boolean lowerCollided) {
		this.lowerCollided = lowerCollided;
	}

	public float getCurrX() {
		return currX;
	}

	public void setCurrX(float currX) {
		this.currX = currX;
	}

	public float getCurrY() {
		return currY;
	}

	public void setCurrY(float currY) {
		this.currY = currY;
	}

	public Door getDestDoor() {
		return destDoor;
	}

	public void setDestDoor(Door destDoor) {
		this.destDoor = destDoor;
	}

}
