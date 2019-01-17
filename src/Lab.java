
public class Lab extends Statics {

	public Lab(float x, float y, float width, float height, float health,
			Handler handler) {
		super(x, y, width, height, health, handler);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	//width =height 1f/8f
	@Override
	public void render() {
		Renderer.renderImage(Assets.staticEntities,x-handler.getXOffset(),y-handler.getYOffset(), width, height,new float[]{0,24f/32f,8f/32f,24f/32f,8f/32f,1f,0,1} ,1.0f);
		//Renderer.renderImage(Assets.staticEntities,x-handler.getXOffset(),y-handler.getYOffset(), width, height,Creature.fillData(0, 24f/32f, 32f, 32f, 8f) ,1.0f);

	}
	
	

}
