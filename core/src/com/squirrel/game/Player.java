package com.squirrel.game;

public class Player {
	
	private int wood;
	private int stone;
	private int lives;
	
	public Player (int wood, int stone, int lives) {
		this.wood = wood;
		this.stone = stone;
		this.lives = lives;
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
	 * @param lives The amount of lives to decrease
	 */
	public void decreaseLives(int amount) {
		lives -= amount;
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
		stone -= amount;
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
		wood -= amount;
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
