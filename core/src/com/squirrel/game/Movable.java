package com.squirrel.game;

public interface Movable {

	/**
	 * Determines if the moving object has reached the goal
	 * that is was sent to arrive at.
	 * @return True if the goal has been reached, else false
	 */
	public boolean hasReachedGoal();
}
