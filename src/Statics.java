
public abstract class Statics extends Entity{

	public Statics(float x, float y, float width, float height, float health,
			Handler handler) {
		super(x, y, width, height, health, handler);
	}

	
	public abstract void update() ;
		
	

	public abstract void render() ;
		
	
	
}
