/**
 * The end menu screen.
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class EndScreen implements Screen {

	private Stage stage;
	private Game game;
	static SelectBox<String> difficulty;
	
	public EndScreen(Game game) {
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

		TextureRegion background = new TextureRegion(new Texture(Gdx.files.internal("level1final.png")));
		Image image = new Image(background);
		image.setWidth(800);
		image.setHeight(480);

		table.addActor(image);

		
		TextButton playButton = new TextButton("Play Again?", skin);
		playButton.sizeBy(50, 25);
		playButton.setX((stage.getWidth()-playButton.getWidth())/2);	//Centers the button
		playButton.setY((stage.getHeight()-playButton.getHeight())/2);
		table.addActor(playButton);

	    //Creates a SelectBox that displays the Array.
	    String[] array = new String[3];
	    array[0] = "Normal (100 Lives)";
	    array[1] = "Hard (20 Lives)";
	    array[2] = "Vincent (1 Life)";

	    difficulty = new SelectBox<String>(skin);
	    difficulty.sizeBy(150, 5);
	    difficulty.setItems(array);
	    difficulty.setX((stage.getWidth()-difficulty.getWidth())/2);
	    difficulty.setY(playButton.getY() - difficulty.getHeight() - 10);
	    table.addActor(difficulty);
	    
	    Label result;
	    if(GameScreen.hasWon){
	    	result = new Label("Congratulations! You won!", skin);
	    }
	    else{
	    	result = new Label("You lose! Maybe try on an easier difficulty?", skin);
	    }
	    result.setX((stage.getWidth()-result.getWidth())/2);
	    result.setY(playButton.getY() + playButton.getHeight() + result.getHeight());
	    table.addActor(result);
	    
	    
	    Label header = new Label("Team 6: Squirrel Tower Defense", skin);
	    Label lead = new Label("Lead Programmer.......................................................Jacob Rust", skin);
	    Label art = new Label("Lead Artist and Animator...........................................Bryan Jarrel", skin);
	    Label assist = new Label("Assistant Programmer and Main Tester..................Vincent Liu", skin);
	    
	    
	    header.setX((stage.getWidth()-header.getWidth())/2);
	    header.setY(stage.getHeight() - header.getHeight()-40);
	    
	    assist.setX((stage.getWidth()-assist.getWidth())/2);
	    
	    
	    lead.setX(assist.getX());
	    lead.setY(header.getY() - lead.getHeight());

	    art.setX(assist.getX());
	    art.setY(lead.getY() - art.getHeight());
	    
	    assist.setY(art.getY() - assist.getHeight());
	  
	  
	    table.addActor(header);
	    table.addActor(lead);
	    table.addActor(art);
	    table.addActor(assist);
	    
	    
	    //Give the button a listener!
	    playButton.addListener(new ClickListener() {
	    	public void clicked(InputEvent event, float x, float y) {
	    		game.setScreen(new GameScreen(game, true));
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
