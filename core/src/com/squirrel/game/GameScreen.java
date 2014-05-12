/**
 * This is the main game screen for Squirrel Defense. All of the 
 * game's main logic should be put into the render function here.
 */
/*
 * 
 * CURRENTLY JUST BEING USED FOR TESTING. WILL CHANGE A LOT
 * 
 *
 * 
 */
package com.squirrel.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
	//The rest of the game scales off these variables
	static final int WIDTH = 1280;
	static final int HEIGHT = 720;
	static final int TILE_SIZE = 80;
	static final int SPAWN_X = 0;
	static final int SPAWN_Y = ScreenInfo.HEIGHT / 2 - ScreenInfo.TILE_SIZE;
	static final int GOAL_X = ScreenInfo.WIDTH - 2 * ScreenInfo.TILE_SIZE; 
	static final int GOAL_Y = ScreenInfo.HEIGHT / 2;
	static final int DIFFICULTY_MODIFIER = 1;
	
	
	//Instance fields
	SpriteBatch batch;
	Texture squirrelImage;
	Texture stickImage;
	Texture towerImage;
	Texture projectileImage;
	OrthographicCamera camera;
	TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	Texture wallImage;
	PathFinder pathFinder;

	Texture trapImage;
	Game game;
	Stage stage;
	Texture mapImage;
	private Sprite mapSprite;
	
	SelectBox<String> structureSelect;
	TextButton nextButton;
	String[] structureList = {"Stick Tower (10)", "Max With His Master's (100)", "Life Tower (50)", "Resource Tower (50)", "Stick Trap (2)",  "SomeTowerWithALongName"};
	
	Array<Enemy> enemies;
	Array<Tower> towers;
	Array<Trap> traps;
	Array<Wave> waves;
	Wave currentWave;
	boolean waveInProgress;
	TiledMapTileLayer mainLayer;
	Player player;
	Label stoneDisplay, woodDisplay, lifeDisplay, errorMessage;
	
	public GameScreen(Game game) {
		this.game = game;
		show();
	}

	@Override
	public void show () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		batch = new SpriteBatch();

		//Create textures for the images 
		squirrelImage = new Texture(Gdx.files.internal("squirrel.png"));
		stickImage = new Texture(Gdx.files.internal("stick.gif"));
		wallImage = new Texture(Gdx.files.internal("wall.png"));
		towerImage = new Texture(Gdx.files.internal("Tower.png"));
		projectileImage = new Texture(Gdx.files.internal("Projectile.png"));
		trapImage = new Texture(Gdx.files.internal("Trap.png"));
		mapImage = new Texture(Gdx.files.internal("level1final.png"));
		
		
		//Setup camera, will be static for this game
		camera = new OrthographicCamera();
		camera.setToOrtho(false, ScreenInfo.WIDTH, ScreenInfo.HEIGHT);
		
		//Lists to hold objects that are created  
		waves = new Array<Wave>();
		traps = new Array<Trap>();
		towers = new Array<Tower>();
		enemies = new Array<Enemy>();
		player = new Player();
		
		//Create default map stuff
		map = new TmxMapLoader().load("level1final.tmx");
		mainLayer = (TiledMapTileLayer) map.getLayers().get(0);
		mapSprite = new Sprite(mapImage);
		mapSprite.setSize(ScreenInfo.WIDTH, ScreenInfo.HEIGHT);
		pathFinder = new PathFinder(mainLayer);
		
		//Add each wave to the array of waves
		waves.add(new WaveOne(mainLayer, player,
				new Vector2(ScreenInfo.toMapCoordinate(SPAWN_X), ScreenInfo.toMapCoordinate(SPAWN_Y)), 
				new Vector2(ScreenInfo.toMapCoordinate(GOAL_X), ScreenInfo.toMapCoordinate(GOAL_Y))));
		waves.add(new WaveTwo(mainLayer, player,
				new Vector2(ScreenInfo.toMapCoordinate(SPAWN_X), ScreenInfo.toMapCoordinate(SPAWN_Y)), 
				new Vector2(ScreenInfo.toMapCoordinate(GOAL_X), ScreenInfo.toMapCoordinate(GOAL_Y))));
		//TODO waves.add(wave2, wave 3, wave 4... so on...
		waveInProgress = false;
		
		//Setup the renderer
		renderer = new OrthogonalTiledMapRenderer(map);
		renderer.setView(camera);
	
		
		//Creates the SelectBox for Tower Selection
		Skin skin = new Skin(Gdx.files.internal("defaultskin.json"));
		structureSelect = new SelectBox<String>(skin);
	    structureSelect.setItems(structureList);
	    structureSelect.sizeBy(150, 5);
	    structureSelect.setX(stage.getWidth()-structureSelect.getWidth());	// USE TO CHANGE THE LOCATION OF THE SELECT BOX
	    structureSelect.setY(stage.getHeight()-structureSelect.getHeight());
	    
	     
	    //Creates the "Next Wave" button
	    nextButton = new TextButton("Next Wave", skin);
	    nextButton.addListener(new ClickListener() {
	    	public void clicked(InputEvent event, float x, float y) {
	    		if (!waveInProgress) {
	    			currentWave = waves.pop();
	    			waveInProgress = true;
	    			enemies = currentWave.getSpawnedEnemies();
	    			errorMessage.setVisible(false);
	    		}
	    	}
	    });
	    nextButton.sizeBy(20, 20);
	    nextButton.setX(stage.getWidth()-nextButton.getWidth());
	    
	    
	    Table table = new Table();
	    table.setFillParent(true);
	    stage.addActor(table);
	    table.addActor(structureSelect);
	    table.addActor(nextButton);
	    
	    
	    lifeDisplay = new Label("Lives: " + player.getLives(), skin);
	    lifeDisplay.setX((stage.getWidth() - lifeDisplay.getWidth())/2);
	    lifeDisplay.setY((stage.getHeight() - nextButton.getHeight()) - 20);
	    table.addActor(lifeDisplay);
	    
	    woodDisplay = new Label("Wood: " + player.getWood(), skin);
	    woodDisplay.setX((stage.getWidth() - lifeDisplay.getWidth())/2);
	    woodDisplay.setY(nextButton.getHeight() + 50);
	    table.addActor(woodDisplay);
	    
	    stoneDisplay = new Label("Stone: " + player.getStone() + "", skin);
	    stoneDisplay.setX((stage.getWidth() - stoneDisplay.getWidth())/2);
	    stoneDisplay.setY(woodDisplay.getY() - stoneDisplay.getHeight());
	    table.addActor(stoneDisplay);
	    
	    errorMessage = new Label("                                                      ", skin);
	    errorMessage.setY(woodDisplay.getY() + errorMessage.getHeight());
	    errorMessage.setVisible(false);
	    table.addActor(errorMessage);
	    

	}

	@Override
	public void render (float delta) {
		//Clears the screen and sets a background;
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Needed
		camera.update();
		renderer.render();
		
		//Determine if wave is over
		if (waveInProgress && currentWave.isOver()) {
			waveInProgress = false;
			player.addWood(currentWave.getWoodReward());
			if (waves.size == 0) {
				game.setScreen(new StartMenuScreen(game));
			}
		} else if (waveInProgress) {
			currentWave.updateMap(mainLayer);
		}
		
		/*
		 * BEGINS RENDERING
		 * Draw all of the objects here
		 */
		renderer.getSpriteBatch().begin();
		mapSprite.draw(renderer.getSpriteBatch());
		
		for (Tower t : towers) {
			t.draw(renderer.getSpriteBatch());
			t.updatePossibleTargets(enemies);
		}
		
		for (Trap t : traps) {
			t.draw(renderer.getSpriteBatch());
			t.updateEnemies(enemies);
		}

		if (waveInProgress) {
			currentWave.draw(renderer.getSpriteBatch());
		}
		renderer.getSpriteBatch().end();
		/*
		 * RENDERING ENDED
		 */
		
		//Try to create structure where touched
		if (Gdx.input.isTouched()) {
			spawnStructure();
		}

		//remove destroyed traps
		for (int i = 0; i < traps.size; i++) {
			if (traps.get(i).isDestroyed()) {
				(mainLayer).setCell(ScreenInfo.toMapCoordinate(traps.get(i).getX()),
						ScreenInfo.toMapCoordinate(traps.get(i).getY()), null);
				traps.removeIndex(i);
			}
		}
		
		//Update enemy paths
		for (Enemy e : enemies) {
			e.updatePath(mainLayer);
		}
		
		//STUFF TO CREATE THE SELECTBOX
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);
		
		lifeDisplay.setText("Lives: " + player.getLives());
		woodDisplay.setText("Wood: " + player.getWood());
		stoneDisplay.setText("Stone: " + player.getStone());
		
		
	}

	/**
	 * Tries to spawn a new structure where the user clicked
	 */
	private void spawnStructure() {
		Vector3 touchPos = new Vector3();
		
		//Get the spot the user touched
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		
		//Convert the units to our camera units
		camera.unproject(touchPos);
		
		//Set in middle of tile
		float xPos = touchPos.x - ScreenInfo.TILE_SIZE / 2;
		float yPos = touchPos.y - ScreenInfo.TILE_SIZE / 2;

		Cell cell = mainLayer.getCell(ScreenInfo.toMapCoordinate(xPos), ScreenInfo.toMapCoordinate(yPos));
		
		
		if (cell == null ||!(cell.getTile().getProperties().containsKey("blocked"))) {
			String structureChosen = structureSelect.getSelected();
			float structX = ScreenInfo.toMapCoordinate(xPos) * ScreenInfo.TILE_SIZE;
			float structY = ScreenInfo.toMapCoordinate(yPos) * ScreenInfo.TILE_SIZE;		
			
			switch (structureChosen) {
			case "Stick Tower (10)": spawnTower(xPos, yPos, new StickTower(structX, structY, enemies));
				break;
			case "Max With His Master's (100)": spawnTower(xPos, yPos, new MaxTower(structX, structY, enemies));
				break;
			case "Life Tower (50)": spawnTower(xPos, yPos, new LifeTower(structX, structY, enemies));
				break;
			case "Resource Tower (50)": spawnTower(xPos, yPos, new ResourceTower(structX, structY, enemies));
				break;
			case "Stick Trap (2)": spawnTrap(xPos, yPos, new StickTrap(structX, structY, enemies));
				break;
			}

			//TODO WHAT IF PLAYER WANTS TO UPGRADE OR DELETE??	
		} else if (cell.getTile().getProperties().containsKey("structure")) {	
			Structure struct = (Structure) cell.getTile().getProperties().get("structure");
		}

		
	}
	
	/**
	 * Spawns a new tower
	 * @param x The x position of the tower
	 * @param y The y position of the tower
	 * @param tower The tower to be spawned
	 */
	private void spawnTower(float x, float y, Tower tower) {
		//Create new structure and put it at that spot
		Cell newCell = new Cell();
		Cell oldCell = mainLayer.getCell(ScreenInfo.toMapCoordinate(x), ScreenInfo.toMapCoordinate(y));
		TextureRegion region = new TextureRegion(tower.getTexture());
		StaticTiledMapTile newTile = new StaticTiledMapTile(region);
		newTile.getProperties().put("blocked", true);
		newCell.setTile(newTile);
		mainLayer.setCell(ScreenInfo.toMapCoordinate(x), ScreenInfo.toMapCoordinate(y), newCell);

		//Check if this will block the path
		//Update pathFinder with the map layer
		pathFinder = new PathFinder(mainLayer);
		
		//If not enough resources OR
		//If no path exists to the goal, do not build the tower.
		 if(player.getWood() < tower.getCost()){
				errorMessage.setText("Insufficient Wood: Cannot Build Tower");
				errorMessage.setX((stage.getWidth() - errorMessage.getWidth())/2);
				errorMessage.setVisible(true);
			}
		
		else if(pathFinder.findShortestPath(
				new Vector2(ScreenInfo.toMapCoordinate(SPAWN_X), ScreenInfo.toMapCoordinate(SPAWN_Y)), 
				new Vector2(ScreenInfo.toMapCoordinate(GOAL_X), ScreenInfo.toMapCoordinate(GOAL_Y))) == null){
			errorMessage.setText("Path Becomes Null: Cannot Build Tower");
			 errorMessage.setX((stage.getWidth() - errorMessage.getWidth())/2);
			errorMessage.setVisible(true);
			mainLayer.setCell(ScreenInfo.toMapCoordinate(x), ScreenInfo.toMapCoordinate(y), oldCell);
			return;
		} 
		else {
			//Update game stuff
			towers.add(tower);
			errorMessage.setVisible(false);
			player.decreaseWood(tower.getCost());
		}
	}
	
	/**
	 * Spawns a new trap
	 * @param x The x position of the trap
	 * @param y The y position of the trap
	 * @param trap The trap to be spawned
	 */
	private void spawnTrap(float x, float y, Trap trap) {
		//Don't let it be built if the player doesnt have enough wood
		if (player.getWood() < trap.getCost()) {
			return;
		}
		
		//Create new structure and put it at that spot
		Cell newCell = new Cell();
		TextureRegion region = new TextureRegion(trap.getTexture());
		StaticTiledMapTile newTile = new StaticTiledMapTile(region);
		newCell.setTile(newTile);
		mainLayer.setCell(ScreenInfo.toMapCoordinate(x), ScreenInfo.toMapCoordinate(y), newCell);

		//Update game stuff
		traps.add(trap);
		player.decreaseWood(trap.getCost());
	}
	
	@Override
	public void dispose() {
		squirrelImage.dispose();
		stickImage.dispose();
		towerImage.dispose();
		wallImage.dispose();
		projectileImage.dispose();
		batch.dispose();
		renderer.dispose();
	}
	
    @Override
    public void resize(int width, int height) {
	}

	@Override
	public void pause() {
    }

   	@Override
    public void resume() {
    }

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
