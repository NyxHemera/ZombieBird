package com.nyxhemera.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.nyxhemera.gameworld.GameRenderer;
import com.nyxhemera.gameworld.GameWorld;
import com.nyxhemera.zbhelpers.InputHandler;

public class GameScreen implements Screen{
	
	private GameWorld world;
	private GameRenderer renderer;
	
	private FPSLogger fps;
	
	private float runTime = 0;
	
	public GameScreen() {
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		
		int midPointY = (int) (gameHeight / 2);
		
		fps = new FPSLogger();
		
		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, (int)gameHeight, midPointY);
		
		Gdx.input.setInputProcessor(new InputHandler(world.getBird()));
		
		Gdx.app.log("GameScreen", "Attached");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		fps.log();
		world.update(delta); //GameWorld updates
		renderer.render(runTime); //GameRenderer renders
		// Convert framerate to String and print it
		//Gdx.app.log("GameScreen FPS", (1/delta) + "");
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
	}

	@Override
	public void dispose() {
		
	}

}
