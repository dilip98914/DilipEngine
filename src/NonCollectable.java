
public abstract class NonCollectable extends Entity{

	public NonCollectable(float x, float  y, float width, float height, float health,Handler handler) {
		super(x, y, width, height, health,handler);
	}

	
	protected boolean isCollectable(){
		return false;
	}
}
