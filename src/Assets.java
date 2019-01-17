import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class Assets {
	private Handler handler;
	private  Texture items;
	public static Texture staticEntities;
	
	public Assets(Handler handler){
		this.handler=handler;
		
		items=textureLoader("res/items.png");
		staticEntities=textureLoader("res/statics.png");
	}
	
	public static Texture textureLoader(String path){
		Texture tex=null;
		try {
			tex=TextureLoader.getTexture("PNG", new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(tex!=null){
			return tex;
		}else{
			Game.printer("this path: "+path+"not found !!");
			return null;
		}
	}

	public Texture getItems() {
		return items;
	}

	public void setItems(Texture items) {
		this.items = items;
	}
	
	
	
}
	
