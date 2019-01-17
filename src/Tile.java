import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public  class Tile {
	
//	private float x,y,width,height;
	public static Texture spriteSheet;//128X128
	private float[] data;
	private boolean solid; 
	private int id;
	public static Texture _tex;
	
	//public static Tile[] tiles=new Tile[256];
	
	
	public Tile(float[] data,boolean solid,int id){
		try {
			spriteSheet=TextureLoader.getTexture("PNG", new FileInputStream("res/tileSetNew.png"));
			_tex=TextureLoader.getTexture("PNG", new FileInputStream("res/firstSprite.png"));
		} catch (FileNotFoundException e) {
			Game.printer("file not found!!");
		} catch (IOException e) {
			Game.printer("io exception !!");
		}
		
		this.data=data;
		this.solid=solid;
		this.id=id;
		
	}
	
	public  void update(){
		
	}
	public  void render(Texture tex,float x,float y,float width,float height,float alpha){
		Renderer.renderImage(tex, x, y, width, height, data,alpha);
	}

	public float[] getData() {
		return data;
	}

	public void setData(float[] data) {
		this.data = data;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
