
public class SafeHouse extends Statics {

	public SafeHouse(float x, float y, float width, float height, float health,
			Handler handler) {
		super(x, y, width, height, health, handler);
	}

	
	public void update() {
		
	}

	
	public void render() {
		Renderer.renderImage(Assets.staticEntities,x-handler.getXOffset(),y-handler.getYOffset(), width, height,new float[]{0,16f/32f,8f/32f,16f/32f,8f/32f,1f-(8f/32f),0,1f-(8f/32f)} ,1.0f);
	}

}
