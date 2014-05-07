/**
 * A wave of enemies that try to get to the goal
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.graphics.g2d.Batch;
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
	
	public Wave (String message, int woodReward,
			int stoneReward, Vector2 spawn, Vector2 goal) {
		
		//this.enemies = enemies;
		this.message = message;
		this.woodReward = woodReward;
		this.stoneReward = stoneReward;
		this.spawn = spawn;
		this.goal = goal;
		
		spawnedEnemies = new Array<Enemy>();
	}
	
	
	public void draw(Batch batch) {
		if (TimeUtils.nanoTime() - lastSpawnTime > spawnTimer)
			spawnNewEnemy();
		for (Enemy enemy : spawnedEnemies) 
			enemy.draw(batch);
	}
	
	public void spawnNewEnemy() {
		if (!(enemies.size < 1))
			spawnedEnemies.add(enemies.pop());
	}
	

	public Array<Vector2> createPath() {
		PathFinder pathFinder = new PathFinder();
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
}
