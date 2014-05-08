package com.squirrel.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.squirrel.game.SquirrelDefenseGame;
import com.squirrel.game.SquirrelGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Squirrel Defense";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new SquirrelDefenseGame(), config);
//		new LwjglApplication(new SquirrelDefenseGame(), config);
	}
}
