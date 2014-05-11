package com.squirrel.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InGameMenu extends Table {
	
	public InGameMenu() {
		setSize(ScreenInfo.WIDTH, ScreenInfo.TILE_SIZE * 2);
		setPosition(0, 0);
//		setFillParent(true);
		
		 //CREATE BUTTONS IMAGES WHEN PRESSED DOWN AND UP
	    TextureRegionDrawable upRegion = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Tower.png"))));
	    TextureRegionDrawable downRegion = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Trap.png"))));
	    
	    //Create and setup style for the button!
	    ButtonStyle style = new ButtonStyle();
	    style.up = upRegion;
	    style.down = downRegion;
	    
	    //Create button with style and add it to the table!
	    Button button = new Button(style);
	    add(button);
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    row();
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    add(new Button(style));
	    
	  //Give the button a listener!
	    button.addListener(new ClickListener() {
	    	public void clicked(InputEvent event, float x, float y) {
	    		Gdx.app.log("In game button: ", "touched");
	    	}
	    });
	}
	
}
