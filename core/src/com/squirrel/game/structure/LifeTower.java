/**
 * A test tower that restores one health per squirrel killed.
 */

package com.squirrel.game.structure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.squirrel.game.enemy.Enemy;

public class LifeTower extends Tower {
	//Change these values for balancing
	public static final int COST = 75;
	static final float DAMAGE = 0;
	static final float RANGE = 0;
	static final float ATTACK_RATE = 0;
	static final float PROJECTILE_SPEED = 0;
	static final FileHandle TOWER_IMAGE = Gdx.files.internal("LifeTower.png");
	static final FileHandle PROJECTILE_IMAGE = Gdx.files.internal("Projectile.png");
	static final String FILE_NAME = "lifeTower1.atlas";
	
	/**
	 * Constructs a new basic tower
	 * @param x The x position of the tower
	 * @param y The y position of the tower
	 * @param possibleTargets An array of possible targets the tower can hit
	 */
	public LifeTower(float x, float y, Array<Enemy> possibleTargets) {
		super(new Sprite(new Texture(TOWER_IMAGE)), x, y, COST, DAMAGE, RANGE, 
				ATTACK_RATE, PROJECTILE_SPEED,
				new Sprite(new Texture(PROJECTILE_IMAGE)), possibleTargets, FILE_NAME);
	}
}
