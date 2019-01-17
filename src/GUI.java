import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

//later we can make gui designer WOW..
public class GUI {
	private boolean clickable;//buttons- animation,sound
	private Font awtFont;
	private TrueTypeFont font;
	private int textSize;
	
	public GUI(){}
	public void init(int textSize){
		this.textSize=textSize;
		awtFont=new Font("Verdana",Font.PLAIN,textSize);

		font=new TrueTypeFont(awtFont,false);

	}
	
	public String textArea(float x,float y,float width,float height, float[] color,String text){//for commandPrompt
		Renderer.renderQuad(color, x, y, width, height);
		Renderer.renderRect(new float[]{0f,0f,0f,1f}, x, y, width, height);
		font.drawString(x+2, y-4, text);
		return text;
		
	}
	
	public String variableTextArea(float x,float y,float[] color,Color textColor,String text){
		int w=font.getWidth(text+10);
		int h=font.getHeight(text);
		Renderer.renderQuad(color, x, y, w, h);
		Renderer.renderRect(new float[]{0f,0f,0f,1f}, x, y, w, h);
		font.drawString(x, y, text, textColor);
		return text;
	}
	
	public String texturedArea(Texture texture,float[] data,float x,float y,float width,float height,String text){//for icons and inventory
		Renderer.renderImage(texture, x, y, width, height, data,1f);//single texture so width=total width and height=total height
		int w=font.getWidth(text);
		int h=font.getHeight(text);
		font.drawString(x+width/2-w/2, y+height/2-h/2, text);
		return text;
		
	}

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	public Font getAwtFont() {
		return awtFont;
	}

	public void setAwtFont(Font awtFont) {
		this.awtFont = awtFont;
	}

	public TrueTypeFont getFont() {
		return font;
	}

	public void setFont(TrueTypeFont font) {
		this.font = font;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	
	
}
