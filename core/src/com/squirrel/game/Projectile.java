/**
 * A projectile is a sprite that travels at a set speed towards
 * a target.
 * 
 * @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Sprite implements Movable {
	private float speed;
	private Enemy target;
	private Vector2 velocity;
	private boolean reachedTarget;
	
	/**
	 * Constructs a new projectile
	 * @param sprite The sprite that will represent the projectile graphically
	 * @param x The x position that the projectile will start at
	 * @param y The y position that the projectile will start at
	 * @param target The target that the projectile is trying to hit
	 * @param speed The speed that the projectile moves at
	 */
	public Projectile(Sprite sprite, float x, float y, Enemy enemy, float speed) {
		super(sprite);
		setX(x);
		setY(y);
		this.target = enemy;
		this.speed = speed;
		velocity = new Vector2();
		reachedTarget = false;
	}

	@Override
	public void draw(Batch batch) {
		//Update the projectile based off the time between 
		//this frame and the last frame
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	/**
	 * Updates the position of the projectile
	 * @param delta Time step
	 */
	public void update(float delta) {
		//Don't update if it has reached the target
		if (target != null && reachedTarget()) {
			return;
		}
		
		//Set x velocity 
		if (Math.abs(getX() - target.getX()) < 1) {
			velocity.x = 0;
		} else if (getX() < target.getX()) {
			velocity.x = speed;
		} else if (getX() > target.getX()) {
			velocity.x = -speed;
		}
		
		//Set y velocity
		if (Math.abs(getY() - target.getY()) < 1) {
			velocity.y = 0;
		} else if (getY() < target.getY()) {
			velocity.y = speed;
		} else if (getY() > target.getY()) {
			velocity.y = -speed;
		} 

		//Update position
		setX(getX() + velocity.x * delta);
		setY(getY() + velocity.y * delta);
	}
	
	/**
	 * Determines if the enemy has reached the target OR
	 * if there target is dead / reached its goal
	 * @return True if the target has been reached, else false
	 */
	public boolean reachedTarget() {
		return ((Math.abs(getX() - target.getX()) < 2
				&& Math.abs(getY() - target.getY()) < 2)) || target.isDead()
				|| target.hasReachedGoal(); 
	}
	
	/**
	 * Determines if the movable object has reached
	 * the goal it was to arrive at
	 */
	public boolean hasReachedGoal() {
		return reachedTarget();
	}
	
	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the target
	 */
	public Sprite getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Enemy target) {
		this.target = target;
	}
}
