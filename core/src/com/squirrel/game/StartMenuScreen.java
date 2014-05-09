/**
 * A bare bones start menu, must change
 */
package com.squirrel.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StartMenuScreen implements Screen {

	private Stage stage;
	Game game;
	
	public StartMenuScreen(Game game) {
		this.game = game;
		create();
	}
	
	public void create () {
	    stage = new Stage();
	    Gdx.input.setInputProcessor(stage);

	    Table table = new Table();
	    table.setFillParent(true);
	    stage.addActor(table);

	    //CREATE BUTTONS IMAGES WHEN PRESSED DOWN AND UP
	    TextureRegionDrawable upRegion = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("wall.png"))));
	    TextureRegionDrawable downRegion = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Trap.png"))));
	    
	    //Create and setup style for the button!
	    ButtonStyle style = new ButtonStyle();
	    style.up = upRegion;
	    style.down = downRegion;
	    
	    //Create button with style and add it to the stage!
	    Button button = new Button(style);
	    button.setX(stage.getWidth()/2);
	    button.setY(stage.getHeight()/2);
	    stage.addActor(button);
	
	    
	    //Give the button a listener!
	    button.addListener(new ClickListener() {
	    	public void clicked(InputEvent event, float x, float y) {
	    		game.setScreen(new GameScreen(game));
	    	}
	    });
	    
	}

	public void resize (int width, int height) {
//	    stage.getViewport().update(width, height, true);
	}

	public void render (float delta) {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();

	    Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}

	public void dispose() {
	    stage.dispose();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
}
