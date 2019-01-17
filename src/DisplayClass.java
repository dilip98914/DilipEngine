import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;




public class DisplayClass {
	public DisplayClass(String title,int width,int height){
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			Game.printer("Display is not set correctly");
			System.exit(0);
		}
		
		
		//init opengl
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_LIGHTING);
		glDisable(GL_DEPTH_TEST);
		
		glClearColor(0f,0f,0f,0f);
		glClearDepth(1);
		
		//enable transparency
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		
		glViewport(0,0,width,height);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,width,height,0,1,-1);
		
		glMatrixMode(GL_MODELVIEW);
		
		
		
	}
	
	
}
