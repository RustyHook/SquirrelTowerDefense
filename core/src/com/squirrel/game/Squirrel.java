/**
 * This class represents a basic squirrel that is an enemy
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Squirrel extends Enemy {
	//Change these values for balancing
	static final float SPEED = 60*2;
	static final float HEALTH = 10;
	static final int REWARD = 2;
	static final FileHandle IMAGE = Gdx.files.internal("squirrel.png");
	static final TextureAtlas a = new TextureAtlas(Gdx.files.internal("squirrelSheet.atlas"));
	static final Array<Sprite> spriteArray = a.createSprites();
	
	/**
	 * Constructs a new squirrel with a sprite, x and y coordinates, and
	 * a path to follow. The path will be traveled in the reverse of the 
	 * order that it is passed in.  
	 * PRECONDITIONS: The x and y coordinates should be within the map the 
	 * squirrel will be drawn to. The path should contain vectors that are
	 * in the map as well.
	 * @param sprite Sprite representing the squirrel
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param squirrelPath Path the squirrel will follow in reverse order
	 */
	public Squirrel(int x, int y, Vector2 goal, Array<Vector2> path) {
		//super(new Sprite(new Texture(IMAGE)), x, y, HEALTH, SPEED, REWARD, goal, path);
		super(spriteArray.get(0), x, y, HEALTH, SPEED, REWARD, goal, path);
	}
	
	//For testing
	public String toString() {
		String out = "";
		out += "\nSquirrel: " + super.toString();
		return out;
	}
}
