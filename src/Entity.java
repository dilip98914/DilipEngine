import org.lwjgl.util.Rectangle;




public abstract class Entity {
	public float x,y,width,height;
	public Rectangle hitBox;//not intialized here
	public float health;//u know what i mean
	protected Handler handler;
	protected float moveX,moveY;
	
	public Entity(float x,float y,float width,float height,float health,Handler handler){
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.health=health;
		this.handler=handler;
		hitBox=new Rectangle();
		
	}
	
	public abstract void update();
	
	public abstract void render();

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
	
	public boolean checkEntityCollision(float xOff,float yOff){
		for(Entity e:EntityManager.entities){
			//Game.printer(e.getClass().getSimpleName());
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f,0f).intersects(this.getCollisionBounds(xOff,yOff)) ){
				//Game.printer(e.getClass().getSimpleName()+" "+this.getClass().getSimpleName());
				Game.printer("true");

				return true;
				//Game.printer("true");
			}
		}
		return false;  
	}
	
	public Rectangle getCollisionBounds(float xOff,float yOff){//bounds where to move
		return new Rectangle((int)(x+hitBox.getX()+xOff),(int)(y+hitBox.getY()+yOff),hitBox.getWidth(),hitBox.getHeight());
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getMoveX() {
		return moveX;
	}

	public void setMoveX(float moveX) {
		this.moveX = moveX;
	}

	public float getMoveY() {
		return moveY;
	}

	public void setMoveY(float moveY) {
		this.moveY = moveY;
	}
	
	

	
	
	
	
}

