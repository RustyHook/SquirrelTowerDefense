package com.squirrel.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Trap extends Structure {
	
	/**
	 * Constructs a new trap
	 * @param sprite The sprite that will represent the trap graphically
	 * @param x The x position of the trap
	 * @param y The y position of the trap
	 * @param cost Cost to make the trap
	 */
	public Trap(Sprite sprite, float x, float y, int cost) {
		super(sprite, x, y, cost);
	}
	
}
