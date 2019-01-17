import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;


public abstract class Creature extends Entity {
	
	public float speed;
	public float animSpeed;
	protected long lastTime=System.currentTimeMillis(),now=0,timer=0;
	protected int frameIndex=0;
	protected boolean up,down,right,left;
	protected  String[] inventory=new String[10];
	protected float[] UP=fillData(0,0,1,1,1),DOWN=fillData(1,1,1,1,-1),RIGHT={0,1,0,0,1,0,1,1},LEFT={1,0,1,1,0,1,0,0};
	protected Texture image;
	protected float[][] UP1={fillData(0,2,4,4,1),fillData(1,2,4,4,1),fillData(2,2,4,4,1),fillData(3,2,4,4,1)};
	protected float[][] DOWN1={fillData(0,0,4,4,1),fillData(1,0,4,4,1),fillData(2,0,4,4,1),fillData(3,0,4,4,1)};
	protected float[][] RIGHT1={fillData(0,3,4,4,1),fillData(1,3,4,4,1),fillData(2,3,4,4,1),fillData(3,3,4,4,1)};
	protected float[][] LEFT1={fillData(0,1,4,4,1),fillData(1,1,4,4,1),fillData(2,1,4,4,1),fillData(3,1,4,4,1)};

	
	//animation too
	public Creature(float x, float y, float width, float height, float speed,
			float health,Handler handler) {
		super(x, y, width, height,health,handler);
		this.speed=speed;
		hitBox.setX(13);
		hitBox.setY(13);
		hitBox.setWidth((int) width-26);
		hitBox.setHeight((int) height-26);
		image=Assets.textureLoader("res/playerSheet.png");

	}
	
	
	public void checkCollisionBoundary(){
		if(x<0) x=0;
		if(x>World.width*16-width) x=World.width*16-width;
		
		if(y<0) y=0;
		if(y>World.height*16-height) y=World.height*16-height;
	}
	
	
	public void xMove(){
		
		 if(moveX>0){
				int tx=(int)(x+moveX+hitBox.getX()+hitBox.getWidth())/16;//we'll check if this lies within solid tile
				if(tx>=World.width-1) moveX=0;//to prevent out of index exception
				if(!handler.getGame().getTiles()[handler.getBufferWorld()[(int)(y+hitBox.getY())/16][tx]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[(int)(y+hitBox.getY()+hitBox.getHeight())/16][tx]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[(int)(y+hitBox.getY()+hitBox.getHeight()/2)/16][tx]].isSolid()){
					x+=moveX;

				}

		 }
		 
		 if(moveX<0){
			 int tx=(int)(x+moveX+hitBox.getX())/16;//we'll check if this lies within solid tile
				if(!handler.getGame().getTiles()[handler.getBufferWorld()[(int)(y+hitBox.getY())/16][tx]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[(int)(y+hitBox.getY()+hitBox.getHeight())/16][tx]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[(int)(y+hitBox.getY()+hitBox.getHeight()/2)/16][tx]].isSolid()){
					x+=moveX;

				}
			 
		 }
		
		
		
	}
	
	public void yMove(){
		
		 
		 if(moveY>0){
				int ty=(int)(y+moveY+hitBox.getY()+hitBox.getHeight())/16;//we'll check if this lies within solid tile
				if(ty>=World.height-1) moveY=0;//to prevent out of index exception
				if(!handler.getGame().getTiles()[handler.getBufferWorld()[ty][(int)(x+hitBox.getX())/16]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[ty][(int)(x+hitBox.getX()+hitBox.getWidth())/16]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[ty][(int)(x+hitBox.getX()+hitBox.getWidth()/2)/16]].isSolid()){
					y+=moveY;

				}

		 }
		 
		 if(moveY<0){
			 int ty=(int)(y+moveY+hitBox.getY())/16;//we'll check if this lies within solid tile
				if(!handler.getGame().getTiles()[handler.getBufferWorld()[ty][(int)(x+hitBox.getX())/16]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[ty][(int)(x+hitBox.getX()+hitBox.getWidth())/16]].isSolid()
						&& !handler.getGame().getTiles()[handler.getBufferWorld()[ty][(int)(x+hitBox.getX()+hitBox.getWidth()/2)/16]].isSolid()){
					y+=moveY;

				}
			 
		 }
	}

	public String[] getInventory() {
		return inventory;
	}

	public void setInventoryIteam(String iteam,int index) {
		this.inventory[index] = iteam;
	}
	
	//not for rotation of images 
	public static float[] fillData(float x0,float y0,float constX,float constY,float unitLen){//only for uniform texture like player for now ...lets see
		return new float[]{x0/constX,y0/constY,(x0/constX+unitLen/constX),y0/constX,(x0/constX+unitLen/constX),(y0/constY+unitLen/constY),x0/constX,(y0/constY+unitLen/constY)};
	}
	
	public void updateAnimation(int leng,float Speed){
		//int k=0;
		now=System.currentTimeMillis();
		timer+=now-lastTime;
		lastTime=now;
		if(timer>=Speed){
			frameIndex++;
			if(frameIndex>=leng){
				frameIndex=0;
			}
			timer=0;
			
		

			
		}
		
		//return k;
	}
	

}
