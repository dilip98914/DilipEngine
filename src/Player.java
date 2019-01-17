import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;




public class Player extends Creature {
	private int xOffset,yOffset;
	private int counter=0;
	private boolean startSound=false;
	/**
	 * 
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @param moveX
	 * @param moveY
	 * @param health
	 */
	
	public Player(float x, float y, float width, float height, float speed, 
			float health,Handler handler) {
		super(x, y, width, height, speed, health,handler);
		//image=Assets.textureLoader("res/Thirty.png");
		
		//sound.playAsSoundEffect(1.0f, 1.0f,false);

	}

	
	public void updateCameraPosition(){
		xOffset=(int)(x+width/2-Game.width/2);
		yOffset=(int)(y+height/2-Game.height/2);
		
		if(xOffset<0) xOffset=0;
		if(xOffset>World.width*16-Game.width) xOffset=World.width*16-Game.width;
		
		if(yOffset<0) yOffset=0;
		if(yOffset>World.height*16-Game.height) yOffset=World.height*16-Game.height;
		Game.printer(Integer.toString((int)4.7));
	}
	


	
	public void update() {
		updateCameraPosition();
		checkCollisionBoundary();		getinput();
		if(moveX>0 || moveX<0 || moveY>0 || moveY<0)
			updateAnimation(4,110);
		
		
		//if(moveX>0 ) {
		//	rightFrameIndex=updateAnimation(4,110);
			
		//}	
		//if(moveX<0 ) {
		//	leftFrameIndex=updateAnimation(4,110);
			
		//}	
		//if(moveY>0 ) {
		//	downFrameIndex=updateAnimation(4,110);
			
	//	}	
		//if(moveY<0 ) {
		//	upFrameIndex=updateAnimation(4,110);
			
	//	}	

		if(!checkEntityCollision(moveX,0)){
			xMove();
			
		}
		
		if(!checkEntityCollision(0,moveY)){
			yMove();


		}
		
		if(moveX!=0 || moveY!=0){
			startSound=true;
		}else{
			startSound=false;
		}
		
		
		if(startSound && counter>=110 ){
			Sound.play(Sound.defaultWalkingSound, 1.0f,1.0f,false);
			startSound=false;
			counter=0;
		}

		counter+=6;
	}

	public void getinput(){
		moveX=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			moveX=speed;
			right=true;
			left=up=down=false;
		}
		 if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			moveX=-speed;
			left=true;
			right=up=down=false;
		 }
		 
		 moveY=0;
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				moveY=speed;
				down=true;
				left=up=right=false;
			 }
			 if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				moveY=-speed;
				up=true;
				left=right=down=false;
			 }
	}
	
	
	


	public void render() {
		int tx=1;
		if(tx==1 && !up && !down && !right && !left){
			Renderer.renderImage(image, x-xOffset, y-yOffset, width, height,DOWN1[frameIndex],1);
		}
		
		if(up){
			tx=0;
		Renderer.renderImage(image, x-xOffset, y-yOffset, width, height,UP1[frameIndex],1);
		}
		
		if(down){
			tx=0;

			Renderer.renderImage(image, x-xOffset, y-yOffset, width, height,DOWN1[frameIndex],1);
			}
		
		if(right ){
			tx=0;

			Renderer.renderImage(image, x-xOffset, y-yOffset, width, height,RIGHT1[frameIndex],1);
			}
		
		if(left){
			tx=0;

			Renderer.renderImage(image, x-xOffset, y-yOffset, width, height,LEFT1[frameIndex],1);
			}
		
		
		//important but according to gap in texture loaded
		Renderer.renderRect(new float[]{0,0,0,1}, x-handler.getXOffset()+hitBox.getX(),y-handler.getYOffset()+hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());

	}


	public int getxOffset() {
		return xOffset;
	}


	public int getyOffset() {
		return yOffset;
	}


	public boolean isStartSound() {
		return startSound;
	}


	public void setStartSound(boolean startSound) {
		this.startSound = startSound;
	}

	
	
	
}
