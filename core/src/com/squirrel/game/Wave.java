/**
 * A wave of enemies that try to get to the goal
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Wave {
	static final long spawnTimer = 1000000000;
	
	private Array<Enemy> enemies;
	private Array<Enemy> spawnedEnemies;
	private long lastSpawnTime;
	
	private String message;
	private int woodReward;
	private int stoneReward;
	private Vector2 spawn;
	private Vector2 goal;
	private Array<Vector2> path;
	
	/**
	 * Constructs a new wave of enemies 
	 * @param map The map the enemies will move on
	 * @param message A message about the map
	 * @param woodReward Wood that is rewarded when the wave is completed
	 * @param stoneReward Stone that is rewarded when the wave is completed 
	 * @param spawn Position that the enemies will spawn at IN MAP TILES COORDINATES
	 * @param goal Position that the enemies are trying to reach IN MAP TILES COORDINATES
	 */
	public Wave (TiledMapTileLayer mapLayer, String message, int woodReward,
			int stoneReward, Vector2 spawn, Vector2 goal) {
		
		this.message = message;
		this.woodReward = woodReward;
		this.stoneReward = stoneReward;
		this.spawn = spawn;
		this.goal = goal;
		
		setPath(new PathFinder(mapLayer).findShortestPath(spawn, goal));
		spawnedEnemies = new Array<Enemy>();
	}
	
	
	public void draw(Batch batch) {
		if (TimeUtils.nanoTime() - lastSpawnTime > spawnTimer) {
			spawnNewEnemy();
		}
		
		for (int i = 0; i < spawnedEnemies.size; i++) {
			if (spawnedEnemies.get(i).isDead() || 
					spawnedEnemies.get(i).hasReachedGoal()) {
				spawnedEnemies.removeIndex(i);
			} else {
				spawnedEnemies.get(i).draw(batch);
			}
		}
	}
	
	public void spawnNewEnemy() {
		if (!(enemies.size < 1))
			spawnedEnemies.add(enemies.pop());
		lastSpawnTime = TimeUtils.nanoTime();
	}
	
	public void setEnemies(Array<Enemy> enemies) {
		this.enemies = enemies;
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
