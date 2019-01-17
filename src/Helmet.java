import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class Helmet extends CollectableEntity {
	private Texture tex;//testing purposes
	
	public Helmet(float x, float y, float width, float height, float health,
			Handler handler) {
		super(x, y, width, height, health, handler);
		try {
			tex=TextureLoader.getTexture("PNG", new FileInputStream("res/helmet.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		hitBox.setX(3);
		hitBox.setY(3);
		hitBox.setWidth((int)width-4);
		hitBox.setHeight((int)height-6);
		

		
	}

	
	public void update() {
		

	}

	
	public void render() {
		
		Renderer.renderImage(tex, x-handler.getPlayer().getxOffset(), y-handler.getPlayer().getyOffset(), width,height, new float[]{0,0,1,0,1,1,0,1},1);
		//Renderer.renderRect(new float[]{1,0,0,1}, x-handler.getPlayer().getxOffset()+3,y-handler.getPlayer().getyOffset()+3, width-4, height-6);

	}

}
