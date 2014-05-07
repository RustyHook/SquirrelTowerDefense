/*
 * NOT SURE IF WE WILL IMPLEMENT THIS
 * 
 * NOT FINISHED
 * 
 * 
 *  @author Jacob Rust
 */

package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Morawski extends Sprite implements InputProcessor {

	private Vector2 velocity;
	private float speed = 60 * 2;
	
	public Morawski(Sprite sprite) {
		super(sprite);
	}
	
	public void draw(SpriteBatch batch) {
		update(Gdx.graphics.getDeltaTime());
	}
	
	public void update(float delta) {
		
		setX(getX() + velocity.x * delta);
		setY(getY() + velocity.y * delta);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
