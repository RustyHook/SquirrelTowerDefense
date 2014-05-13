/**
 * The four wave of the game.
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WaveFour extends Wave {
	//Change these for balancing
	static final int NUM_OF_CHICK_SQUIRRELS = 20;
	static final int NUM_OF_WILD_SQUIRRELS = 10;
	static final int WOOD_REWARD = 20;
	static final int STONE_REWARD = 10;
	static final String MESSAGE = "Wave 4: "
			+NUM_OF_CHICK_SQUIRRELS+" Squirrels that ate"+" Chick-Fil-A (HP: "+ChickfilASquirrel.HEALTH + ") and "
			+NUM_OF_WILD_SQUIRRELS+ " Wild Squirrels (HP: "+WildSquirrel.HEALTH + ")";
	/**
	 * Constructs an object representing the first wave
	 * @param mapLayer The map layer the enemies will move on
	 * @param spawn Position that the enemies will spawn at IN MAP TILES COORDINATES
	 * @param goal Position that the enemies are trying to reach IN MAP TILES COORDINATES
	 */
	public WaveFour(TiledMapTileLayer mapLayer, Player player, Vector2 spawn, Vector2 goal) {
		super(mapLayer, player, MESSAGE, WOOD_REWARD, STONE_REWARD, spawn, goal);
		createEnemies();
	}
	
	/**
	 * Creates and sets the enemies for this wave
	 */
	protected void createEnemies() {
		Array<Enemy> enemies = new Array<Enemy>();
		
		//Create the appropriate amount of squirrels
		for (int i = 0; i < NUM_OF_CHICK_SQUIRRELS; i++) {
			//The squirrels position must be converted back to screen coordinates
			enemies.add(new ChickfilASquirrel(ScreenInfo.toScreenCoordinate(getSpawn().x), 
					ScreenInfo.toScreenCoordinate(getSpawn().y), getSpawn(),
					getGoal(), getPath()));
		}
		for (int i = 0; i < NUM_OF_WILD_SQUIRRELS; i++) {
			//The squirrels position must be converted back to screen coordinates
			enemies.add(new WildSquirrel(ScreenInfo.toScreenCoordinate(getSpawn().x), 
					ScreenInfo.toScreenCoordinate(getSpawn().y), getSpawn(),
					getGoal(), getPath()));
		}
		setEnemies(enemies);
	}
}
