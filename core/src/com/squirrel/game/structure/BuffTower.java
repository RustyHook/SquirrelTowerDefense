/**
 * A tower that increases the the amount damage towers do by a certain amount.
 * This stacks additively like the Resource Tower.
 * 
 */

package com.squirrel.game.structure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.squirrel.game.GameScreen;
import com.squirrel.game.enemy.Enemy;

public class BuffTower extends Tower {
	//Change these values for balancing
	public static final int COST = 35;
	static final float DAMAGE = 0;
	static final float RANGE = 0;
	static final float ATTACK_RATE = 0;
	static final float PROJECTILE_SPEED = 0;
	static final FileHandle TOWER_IMAGE = Gdx.files.internal("BuffTower.png");
	static final FileHandle PROJECTILE_IMAGE = Gdx.files.internal("Projectile.png");
	static final String FILE_NAME = "buffTower1.atlas";
	
	/**
	 * Constructs a new basic tower
	 * @param x The x position of the tower
	 * @param y The y position of the tower
	 * @param possibleTargets An array of possible targets the tower can hit
	 */
	public BuffTower(float x, float y, Array<Enemy> possibleTargets) {
		super(new Sprite(new Texture(TOWER_IMAGE)), x, y, COST, DAMAGE, RANGE, 
				ATTACK_RATE, PROJECTILE_SPEED,
				new Sprite(new Texture(PROJECTILE_IMAGE)), possibleTargets, FILE_NAME);
		GameScreen.setDamageMultiplier((GameScreen.getDamageMultiplier()+2)/2);
	}
}

