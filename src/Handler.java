
public class Handler {
	private Game game;
	public Handler(Game game){
		this.game=game;
		
	}
	
	public Game getGame(){
		return game;
	}
	
	public Player getPlayer(){
		return game.getEntityManager().getPlayer();
	}
	
	public Tile getGrassTile(){
		return game.getGrassTile();
	}
	
	public Tile getoakWoodTile(){
		return game.getOakWood();
	}
	
	public int getXOffset(){
		return game.getEntityManager().getPlayer().getxOffset();
	}
	
	public int getYOffset(){
		return game.getEntityManager().getPlayer().getyOffset();
	}
	
	public Entity getHelmet(){
		return game.getEntityManager().getHelmet();
	}
	
	public World getWorld(){
		return game.getWorld();
	}
	
	public int[][] getBufferWorld(){
		return game.getWorld().getBufferWorld();
	}
	
	public LevelEditor getLevelEditor(){
		return game.getLevelEditor();
	}
	public Assets getAssets(){
		return game.getAssets();
	}
	
	public Tile[] getTiles(){
		return game.getTiles();
	}
	
	public NPC getNpc(){
		return game.getEntityManager().getNpc();
	}
	
	public EntityManager getEntityManager(){
		return game.getEntityManager();
	}
	
	public CommandPrompt getCommandPrompt(){
		return game.getCommandPrompt();
	}
	
	public boolean isStartSound(){
		return game.getEntityManager().getPlayer().isStartSound();
	}
}
