/**
 * A structure is a defensive unit that the player can buy.
 * The only thing every structure has is a cost
 * 
 * @author Jacob Rust
 */

package com.squirrel.game.structure;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Structure extends Sprite {
	private int cost;

	/**
	 * Constructs the structure for classes that extend it.
	 * If the cost is less than zero, an exception is thrown
	 * @param sprite The sprite that will represent the structure
	 * @param x The x position of the structure
	 * @param y The y position of the structure
	 * @param cost The cost of the structure 
	 */
	public Structure(Sprite sprite, float x, float y, int cost) {
		super(sprite);
		setX(x);
		setY(y);
		if (cost >= 0) {
			this.setCost(cost);
		} else {
			throw new IllegalArgumentException("Structure cost must be greater than zero.");
		}
	}
	
	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	
	

}
