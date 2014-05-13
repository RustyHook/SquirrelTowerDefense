package com.squirrel.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class SplashScreen implements Screen{
	private Texture         druidTexture;       // #1
    private SpriteBatch     batch;              // #2
    private Game g;
    private TextureAtlas textureAtlas;
	private Animation animation;
	private float elapsedTime = 0;
	private float x = 0;
	private float y = 120;
	private boolean timerIsOn = false;
	private Sound wavSound;
    
    public SplashScreen(Game g){
    	this.g = g;
    	//create();
    	batch = new SpriteBatch();
    }


    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();                  // #5
        batch.draw( new Texture(Gdx.files.internal("backgd.png")), 0, 0);
        
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(elapsedTime, false), Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 - 150);
        try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        batch.end();
        
        
        if(!timerIsOn) {
            timerIsOn = true;
            
            Timer.schedule(new Task() {
               
               @Override
               public void run() {
               	 newScreen();
               }

            }, 3);
        }
        
    }
    
    public void newScreen(){
    	this.g.setScreen(new StartMenuScreen(this.g));
    }


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		//System.out.println("1");
		// TODO Auto-generated method stub
		druidTexture = new Texture(Gdx.files.internal("050.png"));    // #3                // #4
		textureAtlas = new TextureAtlas(Gdx.files.internal("logo.atlas"));
		animation = new Animation(1/50f, textureAtlas.getRegions());
		wavSound = Gdx.audio.newSound(Gdx.files.internal("intro.wav"));
		wavSound.play();
		//batch = new SpriteBatch();  
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


}
