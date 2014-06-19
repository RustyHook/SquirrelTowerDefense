/**
 * A tower is a defensive structure that fires projectiles
 * at enemies that are in its range. The tower must be 
 * supplied an array of enemies that are on the board that it
 * can shoot at.
 * 
 * @author Jacob Rust
 */

package com.squirrel.game.structure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.squirrel.game.GameScreen;
import com.squirrel.game.Projectile;
import com.squirrel.game.enemy.Enemy;

public abstract class Tower extends Structure {

	//Tower properties
	private float damage; 
	private float range;
	private float attackRate;
	private float projectileSpeed;
	
	//Target variables
	private boolean hasTarget;
	private Enemy target;
	private Array<Enemy> possibleTargets;
	
	//Projectile variables
	private long lastFireTime;
	private Array<Projectile> projectiles;
	private Sprite projectileSprite;
	
	//Animation for tower
	private TextureAtlas textureAtlas;
	private Animation animation;
	private float elapsedTime = 0;

	/**
	 * Constructs a new tower
	 * @param sprite The sprite that will represent the tower graphically
	 * @param x The x position of the tower
	 * @param y The y position of the tower
	 * @param cost The cost of the tower
	 * @param damage The damage the tower does
	 * @param range The range in which the tower can shoot
	 * @param attackRate The rate at which the tower shoots
	 * @param projectileSpeed The speed that the projectile travels at
	 * @param projectileSprite The sprite to represent the projectiles graphically
	 * @param possibleTargets Enemies that are on the map that the tower could shoot at 
	 * @param fileName Name of file that holds the sprite sheet
	 */
	public Tower(Sprite sprite, float x, float y, int cost, float damage, float range, 
			float attackRate, float projectileSpeed, Sprite projectileSprite, 
			Array<Enemy> possibleTargets, String fileName) {
		super(sprite, x, y, cost);

		textureAtlas = new TextureAtlas(Gdx.files.internal(fileName));
		animation = new Animation(1/30f, textureAtlas.getRegions());
		
		this.damage = damage;
		this.range = range;
		this.attackRate = attackRate;
		this.projectileSpeed = projectileSpeed;
		this.projectileSprite = projectileSprite;
		this.possibleTargets = possibleTargets;
		hasTarget = false;
		
		projectiles = new Array<Projectile>();
	}

	@Override
	public void draw(Batch batch) {
		//Update the enemy based off the time between 
		//the this frame and the last frame
		update(Gdx.graphics.getDeltaTime(), batch);	
		super.draw(batch);
	}

	/**
	 * Updates the target and projectiles
	 * @param delta Time step
	 * @param batch The sprite batch to draw the projectiles to
	 */
	public void update(float delta, Batch batch) {
		
		//Update the target
		if (hasTarget) {
			if (target == null || !possibleTargets.contains(target, true)
					|| !inRange(target)
					|| target.isDead()) {
				hasTarget = false;
			}
		} else {
			findTarget();
		}
		
		//Launch a projectile if enough time has passed to reload and there
		//is a target
		if (TimeUtils.nanoTime() - lastFireTime > 1000000000/attackRate && hasTarget) {
			launchProjectile();
		}

		//Update the projectiles
		for (int i = 0; i < projectiles.size; i++) {
			if (projectiles.get(i).reachedTarget()) {
				target.takeDamage((int)(damage * GameScreen.getDamageMultiplier()));
//				projectiles.get(i).dispose();
				projectiles.removeIndex(i);
			} else {
				projectiles.get(i).draw(batch);
			}
		}
		
		//Update animation
		elapsedTime += Gdx.graphics.getDeltaTime();
		setRegion(animation.getKeyFrame(elapsedTime, true));
	}

	/**
	 * Finds an enemy that is in the towers range and
	 * sets it as the target. If there is no target found
	 * it does nothing.
	 */
	private void findTarget() {
		for (Enemy enemy : possibleTargets) {
			if (inRange(enemy)) {
				target = enemy;
				hasTarget = true;
				return;
			}
		}
	}
	
	/**
	 * Checks if an enemy is in the towers range
	 * @param enemy Enemy to be checked if in range
	 * @return True if in range, else false
	 */
	private boolean inRange(Enemy enemy) {
		return Math.hypot(Math.abs(getX() - enemy.getX()), Math.abs(getY() - enemy.getY())) <= range;
	}
	
	/**
	 * Launches a projectile at its target
	 */
	private void launchProjectile() {
		projectiles.add(new Projectile(new Sprite(projectileSprite), getX(), getY(), target, projectileSpeed));
		lastFireTime = TimeUtils.nanoTime();
	}
	
	/**
	 * Sets the possible targets to the specified array of enemies
	 * @param targets Array of enemies that are possible targets
	 */
	public void updatePossibleTargets(Array<Enemy> targets) {
		possibleTargets = targets;
	}

	/**
	 * @return the damage
	 */
	public float getDamage() {
		return damage;
	}


	/**
	 * @param damage the damage to set
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}


	/**
	 * @return the range
	 */
	public float getRange() {
		return range;
	}


	/**
	 * @param range the range to set
	 */
	public void setRange(float range) {
		this.range = range;
	}


	/**
	 * @return the attackRate
	 */
	public float getAttackRate() {
		return attackRate;
	}


	/**
	 * @param attackRate the attackRate to set
	 */
	public void setAttackRate(float attackRate) {
		this.attackRate = attackRate;
	}
	
	public void dispose() {
		textureAtlas.dispose();
		getTexture().dispose();
		projectileSprite.getTexture().dispose();
	}
}
