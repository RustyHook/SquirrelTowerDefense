package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WaveOne extends Wave {
	//Change these for balancing
	static final int NUM_OF_SQUIRRELS = 8;
	static final int WOOD_REWARD = 100;
	static final int STONE_REWARD = 10;
	static final String MESSAGE = "Wave 1: "+NUM_OF_SQUIRRELS+" squirrels with"+Squirrel.HEALTH + " health";
	
	public WaveOne(TiledMapTileLayer mapLayer, Vector2 spawn, Vector2 goal) {
		super(mapLayer, MESSAGE, WOOD_REWARD, STONE_REWARD, spawn, goal);
		createEnemies();
	}
	
	public void createEnemies() {
		Array<Enemy> enemies = new Array<Enemy>();
		
		//Log for testing
		Gdx.app.log("Spawn: ", getSpawn().toString());
		
		for (int i = 0; i < NUM_OF_SQUIRRELS; i++) {
			enemies.add(new Squirrel((int) getSpawn().x*ScreenInfo.TILE_SIZE, 
					(int) getSpawn().y*ScreenInfo.TILE_SIZE, getPath()));
		}
		
		setEnemies(enemies);
	}
}
