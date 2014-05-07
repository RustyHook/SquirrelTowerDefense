package com.squirrel.game;

public class ScreenInfo {
	static final int WIDTH = 1280;
	static final int HEIGHT = 720;
	static final int TILE_SIZE = 80;
	
	/**
	 * Converts coordinate from screen WIDTH x HEIGHT to tile coordinate
	 * @param coord The coordinate to convert
	 * @return the converted coordinate value
	 */
	static public int toMapCoordinate(float coord) {
		return ((int) Math.round(coord / TILE_SIZE));
	}
	
	/**
	 * Converts coordinate from screen map coordinate to screen coordinate
	 * @param coord The coordinate to convert
	 * @return the converted coordinate value
	 */
	static public int toScreenCoordinate(float coord) {
		return ((int) coord * TILE_SIZE);
	}
}
