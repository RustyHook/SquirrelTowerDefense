/**
 * The third wave of the game.
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WaveThree extends Wave {
	//Change these for balancing
	
	static final int NUM_OF_ARCTIC_SQUIRRELS = 15;
	static final int NUM_OF_WILD_SQUIRRELS = 15;
	static final int NUM_OF_SQUIRRELS = 10;
	static final int WOOD_REWARD = 20;
	static final int STONE_REWARD = 10;
	static final String MESSAGE = "Wave 3: "
			+NUM_OF_ARCTIC_SQUIRRELS+" Artic Squirrels (HP: "+ArcticSquirrel.HEALTH+")\n"
			+NUM_OF_SQUIRRELS+ " Squirrels (HP: "+Squirrel.HEALTH+")"+ " and "
			+NUM_OF_WILD_SQUIRRELS+ " Wild Squirrels (HP: "+WildSquirrel.HEALTH+")";

	/**
	 * Constructs an object representing the first wave
	 * @param mapLayer The map layer the enemies will move on
	 * @param spawn Position that the enemies will spawn at IN MAP TILES COORDINATES
	 * @param goal Position that the enemies are trying to reach IN MAP TILES COORDINATES
	 */
	public WaveThree(TiledMapTileLayer mapLayer, Player player, Vector2 spawn, Vector2 goal) {
		super(mapLayer, player, MESSAGE, WOOD_REWARD, STONE_REWARD, spawn, goal);
		createEnemies();
	}
	
	/**
	 * Creates and sets the enemies for this wave
	 */
	protected void createEnemies() {
		Array<Enemy> enemies = new Array<Enemy>();
		
		//Create the appropriate amount of squirrels
		for (int i = 0; i < NUM_OF_WILD_SQUIRRELS/2; i++) {
			//The squirrels position must be converted back to screen coordinates
			enemies.add(new WildSquirrel(ScreenInfo.toScreenCoordinate(getSpawn().x), 
					ScreenInfo.toScreenCoordinate(getSpawn().y), getSpawn(),
					getGoal(), getPath()));
		}
		for (int i = 0; i < NUM_OF_ARCTIC_SQUIRRELS; i++) {
			//The squirrels position must be converted back to screen coordinates
			enemies.add(new ArcticSquirrel(ScreenInfo.toScreenCoordinate(getSpawn().x), 
					ScreenInfo.toScreenCoordinate(getSpawn().y), getSpawn(),
					getGoal(), getPath()));
		}
		for (int i = 0; i < NUM_OF_WILD_SQUIRRELS/2; i++) {
			//The squirrels position must be converted back to screen coordinates
			enemies.add(new WildSquirrel(ScreenInfo.toScreenCoordinate(getSpawn().x), 
					ScreenInfo.toScreenCoordinate(getSpawn().y), getSpawn(),
					getGoal(), getPath()));
		}
		for (int i = 0; i < NUM_OF_SQUIRRELS; i++) {
			//The squirrels position must be converted back to screen coordinates
			enemies.add(new Squirrel(ScreenInfo.toScreenCoordinate(getSpawn().x), 
					ScreenInfo.toScreenCoordinate(getSpawn().y), getSpawn(),
					getGoal(), getPath()));
		}
		
		setEnemies(enemies);
	}
}
