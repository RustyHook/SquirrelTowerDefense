package com.squirrel.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WaveOne extends Wave {
	//Change these for balancing
	static final int NUM_OF_SQUIRRELS = 10;
	static final int WOOD_REWARD = 100;
	static final int STONE_REWARD = 10;
	static final String MESSAGE = "Wave 1: "+NUM_OF_SQUIRRELS+" squirrels with"+Squirrel.HEALTH + " health";
	
	private Vector2 spawn;
	private Vector2 goal;
	
	public WaveOne(Vector2 spawn, Vector2 goal) {
		super(MESSAGE, WOOD_REWARD, STONE_REWARD, spawn, goal);
		
		this.spawn = spawn;
		this.goal = goal;
		
		setEnemies();
	}
	
	public void setEnemies() {
		Array<Enemy> enemies = new Array<Enemy>();
		
		for (int i = 0; i < NUM_OF_SQUIRRELS; i++) {
			enemies.add(new Squirrel(spawn.x, spawn.y));
		}
	}
}
