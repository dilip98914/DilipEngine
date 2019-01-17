import java.util.ArrayList;


public class EntityManager {// later for making entities on fly yeahh...
	private Handler handler;
	public static ArrayList<Entity> entities =new ArrayList<Entity>();
	public static ArrayList<Entity> listOfEntities=new ArrayList<Entity>();
	//all entities here yyeah..
	private Player player;
	private NPC npc,npc1,npc2,npc3,npc4;
	private Helmet helmet;
	private Apple apple;
	private GoldenApple goldenApple;
	private Door door;
	private Door door2;
	private Lab lab;
	
	public EntityManager(Handler handler){
		this.handler=handler;
		init();
		addToList();
		addingAllEntities();//using coimmand prompt use to add later
	}
	
	private void init(){
		player=new Player(World.width/2,World.height/2,40,40,1.9f,0,handler);
		npc=new NPC(100,80,40,40,2,0,handler);
		npc1=new NPC(200,50,40,40,2,0,handler);
		npc2=new NPC(200,250,40,40,2,0,handler);
		npc3=new NPC(150,300,40,40,2,0,handler);
		npc4=new NPC(170,70,40,40,2,0,handler);
		helmet=new Helmet(150,180,16,16,0,handler);
		apple=new Apple(100,110,16,16,0,handler);
		goldenApple=new GoldenApple(150,110,16,16,0,handler);
		//door=new Door(400,198,40,10,0,handler,null);
		//door2=new Door(100,199,40,10,0,handler,null);
		//door.setDestDoor(door2);
		//door2.setDestDoor(door);
		lab=new Lab(200,200,8*16,8*16,0,handler);
	}
	
	private  void addingAllEntities(){
		entities.add(player);
		//entities.add(lab);
	//	entities.add(npc);
	//	entities.add(helmet);
	//	entities.add(apple);
	//	entities.add(goldenApple);
	//	entities.add(npc1);
	//	entities.add(npc2);
	//	entities.add(npc3);
	//	entities.add(npc4);
		//entities.add(door);
		//entities.add(door2);


	}
	
	public void addToList(){
		listOfEntities.add(player);
		listOfEntities.add(npc);
		listOfEntities.add(door);
		listOfEntities.add(helmet);
		listOfEntities.add(apple);
		listOfEntities.add(goldenApple);
	}
	
	public void update(){
		for(Entity e:entities){
			e.update();
		}
		
		//Game.printer(Integer.toString(listOfEntities.indexOf(player.getClass().getSimpleName()))+"this is index:");
		//System.out.println(player.getClass().getSimpleName().);

	}
	
	public void render(){
		for(Entity e:entities){
			e.render();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public NPC getNpc() {
		return npc;
	}

	public NPC getNpc1() {
		return npc1;
	}

	public NPC getNpc2() {
		return npc2;
	}

	public NPC getNpc3() {
		return npc3;
	}

	public NPC getNpc4() {
		return npc4;
	}

	public Helmet getHelmet() {
		return helmet;
	}

	public Apple getApple() {
		return apple;
	}

	public GoldenApple getGoldenApple() {
		return goldenApple;
	}
	
	
	
	
}
