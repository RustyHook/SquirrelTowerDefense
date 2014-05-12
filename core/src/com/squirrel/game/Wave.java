/**
 * A wave of enemies that try to get to the goal
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class Wave {
	static final long spawnTimer = 750000000;
	
	private Array<Enemy> enemies;
	private Array<Enemy> spawnedEnemies;
	private long lastSpawnTime;
	
	private String message;
	private int woodReward;
	private int stoneReward;
	private Vector2 spawn;
	private Vector2 goal;
	private Array<Vector2> path;
	private Player player;
	
	/**
	 * Constructs a new wave of enemies 
	 * @param map The map the enemies will move on
	 * @param message A message about the map
	 * @param woodReward Wood that is rewarded when the wave is completed
	 * @param stoneReward Stone that is rewarded when the wave is completed 
	 * @param spawn Position that the enemies will spawn at IN MAP TILES COORDINATES
	 * @param goal Position that the enemies are trying to reach IN MAP TILES COORDINATES
	 */
	public Wave (TiledMapTileLayer mapLayer, Player player, String message, int woodReward,
			int stoneReward, Vector2 spawn, Vector2 goal) {
		this.message = message;
		this.woodReward = woodReward;
		this.stoneReward = stoneReward;
		this.spawn = spawn;
		this.goal = goal;
		this.player = player;
		
		setPath(new PathFinder(mapLayer).findShortestPath(spawn, goal));
		spawnedEnemies = new Array<Enemy>();
	}
	
	
	/**
	 * Draws each enemy in the wave or gets rid of it if it has
	 * reached its goal or is dead. Also spawns new enemies.
	 * @param batch The Batch all of the enemies will draw to
	 */
	public void draw(Batch batch) {
		
		//Spawn new enemy if the right time has passed
		if (TimeUtils.nanoTime() - lastSpawnTime > spawnTimer) {
			spawnNewEnemy();
		}
		
		//Draw spawned enemies and remove ones that are done
		for (int i = 0; i < spawnedEnemies.size; i++) {
			if (spawnedEnemies.get(i).isDead()) {
				player.addWood(spawnedEnemies.get(i).getReward());
				spawnedEnemies.removeIndex(i);
			} else if (spawnedEnemies.get(i).hasReachedGoal()) {
				player.decreaseLives();
				spawnedEnemies.removeIndex(i);
			} else {
				spawnedEnemies.get(i).draw(batch);
			}
		}
	}
	
	/**
	 * Spawns a new enemy and adds it to the wave
	 */
	private void spawnNewEnemy() {
		if (!(enemies.size < 1))
			spawnedEnemies.add(enemies.pop());
		lastSpawnTime = TimeUtils.nanoTime();
	}
	
	public Array<Enemy> getSpawnedEnemies() {
		return spawnedEnemies;
	}
	
	public boolean isOver() {
		return enemies.size == 0 && spawnedEnemies.size == 0;
	}
	
	public void updateMap(TiledMapTileLayer mapLayer) {
		setPath(new PathFinder(mapLayer).findShortestPath(spawn, goal));
	}
	
	/**
	 * This method should create the enemies for the wave
	 * and the set them using setEnemies. 
	 */
	protected abstract void createEnemies();
	
	/**
	 * This method sets the enemies in the wave
	 * @param enemy
	 */
	protected void setEnemies(Array<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public Array<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the woodReward
	 */
	public int getWoodReward() {
		return woodReward;
	}

	/**
	 * @param woodReward the woodReward to set
	 */
	public void setWoodReward(int woodReward) {
		this.woodReward = woodReward;
	}

	/**
	 * @return the stoneReward
	 */
	public int getStoneReward() {
		return stoneReward;
	}

	/**
	 * @param stoneReward the stoneReward to set
	 */
	public void setStoneReward(int stoneReward) {
		this.stoneReward = stoneReward;
	}


	/**
	 * @return the path
	 */
	public Array<Vector2> getPath() {
		return path;
	}


	/**
	 * @param path the path to set
	 */
	public void setPath(Array<Vector2> path) {
		this.path = path;
	}


	/**
	 * @return the spawn
	 */
	public Vector2 getSpawn() {
		return spawn;
	}


	/**
	 * @param spawn the spawn to set
	 */
	public void setSpawn(Vector2 spawn) {
		this.spawn = spawn;
	}


	/**
	 * @return the goal
	 */
	public Vector2 getGoal() {
		return goal;
	}


	/**
	 * @param goal the goal to set
	 */
	public void setGoal(Vector2 goal) {
		this.goal = goal;
	}
}
