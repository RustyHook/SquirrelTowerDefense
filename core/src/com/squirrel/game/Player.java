package com.squirrel.game;

public class Player {
	static final int STARTING_WOOD = 100;
	static final int STARTING_LIVES = 20;
	
	private int wood;
	private int stone;
	private int lives;
	
	public Player () {
		this.wood = STARTING_WOOD;
		this.stone = 0;
		this.lives = STARTING_LIVES;
	}


	public boolean hasLost() {
		return lives <= 0;
	}
	
	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Decreases life by 1
	 */
	public void decreaseLives() {
		lives -= 1;
	}
	
	/**
	 * Increase life by the amount of lifeTowers in the game.
	 */
	
	public void increaseLives() {
		lives += GameScreen.lifeTowers;
	}

	/**
	 * @return the stone
	 */
	public int getStone() {
		return stone;
	}

	/**
	 * Increases the player's stone supply 
	 * @param amount Amount of stone
	 */
	public void addStone(int amount) {
		stone += amount;
	}
	
	/**
	 * Decreases the player's stone supply 
	 * @param amount Amount of stone
	 */
	public void decreaseStone(int amount) {
		stone -= amount;
		if (stone < 0) stone = 0;
	}


	/**
	 * @return the wood
	 */
	public int getWood() {
		return wood;
	}


	/**
	 * Increases the player's wood supply 
	 * @param amount Amount of wood
	 */
	public void addWood(int amount) {
		wood += amount;
	}
	
	/**
	 * Decreases the player's wood supply 
	 * @param amount Amount of wood
	 */
	public void decreaseWood(int amount) {
		wood -= amount;
		if (wood < 0) stone = 0;
	}
	
	
}
