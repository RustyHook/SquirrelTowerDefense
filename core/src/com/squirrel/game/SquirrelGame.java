package com.squirrel.game;

import com.badlogic.gdx.Game;

public class SquirrelGame extends Game
{
        @Override
        public void create()
        {               
                this.setScreen(new GameScreen(this));
        }
}