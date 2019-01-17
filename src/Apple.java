
public class Apple extends CollectableEntity {

	public Apple(float x, float y, float width, float height, float health,
			Handler handler) {
		super(x, y, width, height, health, handler);
		hitBox.setX(3);
		hitBox.setY(3);
		hitBox.setWidth((int)width-5);
		hitBox.setHeight((int)height-5);
	}

	
	public void update() {
		
	}

	
	public void render() {//32 is dividing const
		Renderer.renderImage(handler.getAssets().getItems(), x-handler.getXOffset(),y-handler.getYOffset(), width, height,Creature.fillData(20f, 0f, 32f, 32f, 2f)  ,1);
		//Renderer.renderRect(new float[]{0,0,0,1}, x-handler.getXOffset()+hitBox.getX(),y-handler.getYOffset()+hitBox.getY(), hitBox.getWidth(), hitBox.getHeight());

	}

}
