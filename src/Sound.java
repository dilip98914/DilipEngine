import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;


public class Sound {
	public static Audio defaultWalkingSound;
	public static Audio defaultBackgroundMusic;
	
	
	public static void init(){
		try {
			defaultWalkingSound =AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/res/wood.wav"));
			defaultBackgroundMusic =AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("/res/PalletTown.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void play(final Audio audio,final float amplitude,final float pitch,final boolean loop){
		Thread t = new Thread(){
			public void run(){
				audio.playAsSoundEffect(pitch, amplitude, loop);
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		SoundStore.get().poll(0);
	}
	
}
