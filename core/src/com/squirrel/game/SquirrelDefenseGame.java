///**
// * This is the main game screen for Squirrel Defense. All of the 
// * game's main logic should be put into the render function here.
// */
///*
// * 
// * CURRENTLY JUST BEING USED FOR TESTING. WILL CHANGE A LOT
// * 
// *
// * 
// */

package com.squirrel.game;

import com.badlogic.gdx.Game;

public class SquirrelDefenseGame extends Game {
        @Override
        public void create() {               
                this.setScreen(new StartMenuScreen(this));
        }
}