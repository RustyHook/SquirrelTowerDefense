package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public abstract class Trap extends Structure {
	
	private Array<Enemy> enemies;
	private boolean destroyed;
	
	/**
	 * Constructs a new trap
	 * @param sprite The sprite that will represent the trap graphically
	 * @param x The x position of the trap
	 * @param y The y position of the trap
	 * @param cost Cost to make the trap
	 * @param enemies The enemies traveling on the map that the trap can hit
	 */
	public Trap(Sprite sprite, float x, float y, int cost, Array<Enemy> enemies) {
		super(sprite, x, y, cost);
		this.enemies = enemies;
		destroyed = false;
	}
	
	
	@Override
	public void draw(Batch batch) {
		
		if (!destroyed) {
			for (Enemy enemy : enemies) {
				if (isInTrap(enemy)) {
					activateTrap();
					destroyed = true;
					break;
				}
			}
		}
		
		super.draw(batch);
	}
	
	/**
	 * This method should do
	 * @param enemy
	 */
	protected abstract void activateTrap();
	
	public void updateEnemies(Array<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	protected boolean isInTrap(Enemy enemy) {
		return (Math.abs(enemy.getX() - getX()) < 3 &&
				Math.abs(enemy.getY() - getY()) < 3);
		
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	protected Array<Enemy> getEnemies() {
		return enemies;
	}
	
	
}
