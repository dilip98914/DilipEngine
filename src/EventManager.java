import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class EventManager {
	public static int tKey;//its actually currently pressed key otherise 0
	public static String character="";
	public static int tButton;
	
	public static void checkKeys(){
		tButton=100;
		tKey=0;
		character="";
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				tButton=Mouse.getEventButton();
			}else{
				
			}
		}
		
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				tKey=Keyboard.getEventKey();
				if(Keyboard.getEventCharacter()!=0)//keyevents which return null character 
						character =String.valueOf(Keyboard.getEventCharacter());
			}else{
				
				
			}
	
			
			
		}
		
	}
}
