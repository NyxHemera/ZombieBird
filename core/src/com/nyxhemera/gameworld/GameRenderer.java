package com.nyxhemera.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.nyxhemera.gameobjects.Bird;
import com.nyxhemera.gameobjects.Grass;
import com.nyxhemera.gameobjects.Pipe;
import com.nyxhemera.gameobjects.ScrollHandler;
import com.nyxhemera.zbhelpers.AssetLoader;

public class GameRenderer {
	private GameWorld world;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	
	private int midPointY;
	private int gameHeight;
	
	private Bird bird;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;
	private TextureRegion bg, grass;
	private Animation birdAnimation;
	private TextureRegion birdMid, birdDown, birdUp;
	private TextureRegion skullUp, skullDown, bar;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		this.world = world;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		initGameObjects();
		initAssets();
	}
	
	public void render(float runTime) {
		//First we draw a black background to prevent flickering
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		//draw background color
		shapeRenderer.setColor(55/255.0f, 80/255.0f, 100/255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY+66);
		
		//draw Grass
		shapeRenderer.setColor(111/255.0f, 186/255.0f, 45/255.0f, 1);
		shapeRenderer.rect(0, midPointY+66, 136, 11);
		
		//draw Dirt
		shapeRenderer.setColor(147/255.0f, 80/255.0f, 27/255.0f, 1);
		shapeRenderer.rect(0, midPointY+77, 136, 52);
		
		shapeRenderer.end();
		
		batch.begin();
		
		//disable transparency
		//This is good for performance when drawing images that don't require it
		batch.disableBlending();
		batch.draw(bg, 0, midPointY+23, 136, 43);
		drawGrass();
		drawPipes();
		
		//enable transparency
		batch.enableBlending();
		
		drawSkulls();
		
		if(bird.shouldntFlap()) {
			batch.draw(birdMid, bird.getX(), bird.getY(), bird.getWidth()/2.0f, bird.getHeight()/2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
		}else {
			batch.draw(birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth()/2.0f, bird.getHeight()/2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
		}
		
		
		batch.end();
		
	}
	
	private void initGameObjects() {
		bird = world.getBird();
		scroller = world.getScrollHandler();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
	}
	
	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdMid = AssetLoader.bird;
		birdDown = AssetLoader.birdDown;
		birdUp = AssetLoader.birdUp;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;
	}
	
	private void drawGrass() {
        // Draw the grass
        batch.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batch.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        batch.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batch.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batch.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batch.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        batch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }

}















