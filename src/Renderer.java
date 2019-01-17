

import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;


public class Renderer {
	public static int displayList;
	
	
	public static void init(){
		displayList=glGenLists(1);
	}
	
	public static void renderQuad(float[] color,float x,float y,float width,float height ){
		glDisable(GL_TEXTURE_2D);
		glColor4f(color[0],color[1],color[2],color[3]);
		glNewList(displayList,GL_COMPILE);
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		glEnd();
		glEndList();
		glCallList(displayList);
		glEnable(GL_TEXTURE_2D);

	}
	
	public static void renderRect(float[] color,float x,float y,float width,float height ){
		glDisable(GL_TEXTURE_2D);

		glColor4f(color[0],color[1],color[2],color[3]);
		glNewList(displayList,GL_COMPILE);
		glBegin(GL_LINE_STRIP);
		glVertex2f(x,y);
		glVertex2f(x+width,y);
		glVertex2f(x+width,y+height);
		glVertex2f(x,y+height);
		glVertex2f(x,y);
		glEnd();
		glEndList();
		glCallList(displayList);
		glEnable(GL_TEXTURE_2D);

	}
	
	public static void renderCircle(float x,float y,float r){
		glColor4f(0,0,0,1);
		double k=0;
		glBegin(GL_POINTS);
		for(k=0;k<=360;k+=0.1){
			
		glVertex2f((float)(x+r*Math.cos(Math.toRadians(k))),(float)(y-r*Math.sin(Math.toRadians(k))));
		}
		glEnd();
	}
	
	
	public static void renderFilledCircle(float x,float y,float r,float[] color){
		glColor4f(color[0],color[1],color[2],color[3]);
		double k=0;
		glBegin(GL_LINES);
		for(k=0;k<=360;k+=0.1){
		glVertex2f(x,y);
		glVertex2f((float)(x+r*Math.cos(Math.toRadians(k))),(float)(y-r*Math.sin(Math.toRadians(k))));
		}
		glEnd();
	}

	
	public static void renderImage(Texture texture,float x,float y,float width,float height,float[] data,float alpha){
		
		texture.bind();
		glNewList(displayList,GL_COMPILE);
		glBegin(GL_QUADS);
		glColor4f(1,1,1, alpha);
		glTexCoord2f(data[0],data[1]);
		glVertex2f(x,y);
		
		glTexCoord2f(data[2],data[3]);
		glVertex2f(x+width,y);
		
		glTexCoord2f(data[4],data[5]);
		glVertex2f(x+width,y+height);
		
		glTexCoord2f(data[6],data[7]);
		glVertex2f(x,y+height);
		
		glEnd();
		glEndList();
		glCallList(displayList);
		
	}
	
	//public static void renderSubImage(Texture texture,float x,float y,float width,float height,float[] data){}
	
	
	
	
	public static void destroy(){
		glDeleteLists(displayList,1);
	}
}
