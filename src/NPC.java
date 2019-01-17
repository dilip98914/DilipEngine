import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class NPC extends Creature {

	//random movement variables
	Random random=new Random();
	
	int ticks=0,tick2=0,tick3=0,tickCount,maxTick;
	boolean inHorizontal=true,inDiagonal=false,isStatic;
	int i=0;
	int stateTime;
	float tSpeed,tSpeedStatic;
	
	boolean done=false,staticLeft=false,staticRight=false,staticUp=false,staticDown=false;

	//acc. to given image
	
	public NPC(float x, float y, float width, float height, int speed, float health,
			Handler handler) {
		super(x, y, width, height, speed, health, handler);//48X50
		
		i=random.nextInt(1);
		tickCount=random.nextInt(120);
		maxTick=random.nextInt(200);
		tSpeed=random.nextFloat()+0.2f;
		tSpeedStatic=random.nextFloat()+0.2f;//-1,0,1
		if(tSpeedStatic==0) tSpeedStatic=-0.5f;
		if(random.nextInt(1)==0){
			isStatic=false;
		}else{
			isStatic=true;
		}
		//tSpeed=2;
		stateTime=random.nextInt(200);
		
	}

	
	
	
	public void update() {
		
		checkCollisionBoundary();
		if(!isStatic) randomisedPattern(tSpeed,0);
		if(isStatic) staticPattern(tSpeedStatic,0);
		if(moveX>0 || moveX<0 || moveY>0 || moveY<0)
			updateAnimation(4,110);
		
		if(!checkEntityCollision(moveX,0)){
			xMove();
		}
		
		if(!checkEntityCollision(0,moveY)){
			yMove();
		}
		

		//if(isStatic) staticPattern();
		//if(!isStatic) randomisedPattern(tSpeed,0);
	}

	private void randomisedPattern(float speed,int seed){//directly interfers with moveX,moveY
	//	if(isStatic)
		//	moveX=moveY=0;
		
		if(i==0 && !done){
			inHorizontal=false;
			moveY=speed;
			done=true;
		}
		
		if(i!=0 && !done){
			inHorizontal=true;
			moveX=speed;
			done=true;
		}
		
		if(inHorizontal){
			if(ticks>=tickCount/*or 60*/){
				moveX=0;
				moveY=speed;
				ticks=0;
				inHorizontal=!inHorizontal;
			}
		}
		
		if(!inHorizontal){
			if(ticks>=tickCount){
				moveY=0;
				moveX=speed;
				ticks=0;
				inHorizontal=!inHorizontal;
			}
		}
		
		
		if(tick2>=maxTick){
			tSpeed=-tSpeed;
			tick2=0;
		}
		
		ticks+=1;
		tick2+=1;
		tick3++;
		//if(isStatic)
		//	moveX=moveY=0;
		//if(tick3>=stateTime) isStatic=!isStatic;
	}
	
	private void staticPattern(double speed,int seed){//directly interfers with moveX,moveY
		//	if(isStatic)
			//	moveX=moveY=0;
			
			if(i==0 && !done){
				inHorizontal=false;
				if(speed>0) {staticUp=false;staticDown=true;}
				if(speed<0) {staticDown=false;staticUp=true;}
				done=true;
			}
			
			if(i!=0 && !done){
				inHorizontal=true;
				if(speed>0) {staticRight=true;staticLeft=false;}
				if(speed<0) {staticLeft=true;staticRight=false;}
				done=true;
			}
			
			if(inHorizontal){
				if(ticks>=tickCount/*or 60*/){
					staticRight=staticLeft=false;
					if(speed>0) { staticUp=false;staticDown=true;}
					if(speed<0) {staticDown=false;staticUp=true;}
					ticks=0;
					inHorizontal=!inHorizontal;
				}
			}
			
			if(!inHorizontal){
				if(ticks>=tickCount){
					staticUp=staticDown=false;
					if(speed>0) {staticRight=true;staticLeft=false;}
					if(speed<0) {staticLeft=true;staticRight=false;}
					ticks=0;
					inHorizontal=!inHorizontal;
				}
			}
			
			
			if(tick2>=maxTick){
				tSpeedStatic=-tSpeedStatic;
				tick2=0;
			}
			
			ticks+=2;
			tick2+=2;
			//if(isStatic)
			//	moveX=moveY=0;
			//if(tick3>=stateTime) isStatic=!isStatic;
		}
		
	
	
	public void xMove(){
		
		//moveX=0;
		
		
		//
		
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
		//moveY=0;
	/*	if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			moveY=speed;
			down=true;
			left=up=right=false;
		 }
		 if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			moveY=-speed;
			up=true;
			left=right=down=false;
		 }
		 */
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
	
	public void render() {
		
		
		if(inHorizontal){//right
			if(moveX>0 || staticRight){
				Renderer.renderImage(image, x-handler.getPlayer().getxOffset(), y-handler.getPlayer().getyOffset(), width, height,RIGHT1[frameIndex],1);

			}
			 if(moveX<0 || staticLeft){//left
				Renderer.renderImage(image, x-handler.getPlayer().getxOffset(), y-handler.getPlayer().getyOffset(), width, height,LEFT1[frameIndex],1);

			}
			
		}

		if(!inHorizontal){
			if(moveY>0 || staticDown){
				Renderer.renderImage(image, x-handler.getPlayer().getxOffset(), y-handler.getPlayer().getyOffset(), width, height,DOWN1[frameIndex],1);

			}
			 if(moveY<0 || staticUp ){//left
				Renderer.renderImage(image, x-handler.getPlayer().getxOffset(), y-handler.getPlayer().getyOffset(), width, height,UP1[frameIndex],1);

			}
			
		}
		
//		Renderer.renderRect(new float[]{0,0,0,1}, x-handler.getXOffset()+13,y-handler.getYOffset()+13, width-26, height-26);
		//Renderer.renderRect(new float[]{0,0,0,1}, x-handler.getXOffset()+hitBox.getX(),y-handler.getYOffset()+hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());

	}

}
