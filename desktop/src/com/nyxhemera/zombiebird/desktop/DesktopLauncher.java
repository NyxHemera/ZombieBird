package com.nyxhemera.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nyxhemera.zombiebird.ZBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie Bird";
		config.width = 272;
		config.height = 408;
		//config.backgroundFPS = 0;
		//config.foregroundFPS = 0;
		//config.vSyncEnabled = false;
		new LwjglApplication(new ZBGame(), config);
	}
}
