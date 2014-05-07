package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class StickTrap extends Trap {

	//Change these for balancing
	static final float DAMAGE = 10f;
	static final int COST = 2;
	static final FileHandle TRAP_IMAGE = Gdx.files.internal("Trap.png");
	
	public StickTrap(float x, float y, Array<Enemy> enemies) {
		super(new Sprite(new Texture(TRAP_IMAGE)), x, y, COST, enemies);
	}

	
	@Override
	protected void activateTrap() {
		for (Enemy enemy : getEnemies()) {
			if (isInTrap(enemy)) {
				enemy.takeDamage(DAMAGE);
			}
		}
		
	}
}
