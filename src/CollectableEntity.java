
public abstract  class CollectableEntity extends Entity{
	//public static CollectableEntity[] collectables=new CollectableEntity[1];////
	
	
	public CollectableEntity(float x, float y, float width, float height,float health,Handler handler) {
		super(x, y, width, height,health,handler);
	}
	
	protected boolean isCollectable(){
		return true;
	}
	
	protected boolean isCollected(){
		return false;
	}

	

}
