/**
 * A bare bones start menu, must change
 */
package com.squirrel.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

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
	    //table.setFillParent(true);
	    stage.addActor(table);
	    table.debug();
	    
		Skin skin = new Skin(Gdx.files.internal("defaultskin.json"));

		/*
	    //CREATE BUTTONS IMAGES WHEN PRESSED DOWN AND UP
	    TextureRegionDrawable upRegion = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("wall.png"))));
	    TextureRegionDrawable downRegion = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Trap.png"))));
	    
	    //Create and setup style for the button!
	    ButtonStyle style = new ButtonStyle();
	    style.up = upRegion;
	    style.down = downRegion;

	    //Create button with style and add it to the stage!
	    Button button = new Button(style);
		 */

		TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("level1final.png")));
		Image image = new Image(background);
		image.setWidth(800);
		image.setHeight(480);

		table.addActor(image);

		
		TextButton playButton = new TextButton("Play", skin);
		playButton.sizeBy(50, 25);
		playButton.setX((stage.getWidth()-playButton.getWidth())/2);	//Centers the button
		playButton.setY((stage.getHeight()-playButton.getHeight())/2);
		table.addActor(playButton);

		/*
	    //Creates a SelectBox that displays the Array.
	    String[] array = new String[3];
	    array[0] = "ZERO";
	    array[1] = "ONE";
	    array[2] = "TWO";

	    Skin skin = new Skin(Gdx.files.internal("defaultskin.json"));

	    SelectBox<String> selectBox = new SelectBox<String>(skin);
	    selectBox.setX(200);
	    selectBox.setY(200);
	    selectBox.setItems(array);
	    selectBox.sizeBy(50);
	    stage.addActor(selectBox);
	    */
	    
	    //Give the button a listener!
	    playButton.addListener(new ClickListener() {
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
