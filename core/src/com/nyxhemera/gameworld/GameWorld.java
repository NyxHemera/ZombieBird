package com.nyxhemera.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.nyxhemera.gameobjects.Bird;
import com.nyxhemera.gameobjects.ScrollHandler;
import com.nyxhemera.zbhelpers.AssetLoader;

public class GameWorld {
	
	private Bird bird;
	private ScrollHandler scroller;
	
	private boolean isAlive = true;
	
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		scroller = new ScrollHandler(midPointY + 66);
	}
	
	public void update(float delta)	{
		bird.update(delta);	
		scroller.update(delta);
		
		if(isAlive && scroller.collides(bird)) {
			scroller.stop();
			AssetLoader.dead.play();
			isAlive = false;
		}
		
	}
	
	public Bird getBird() {
		return bird;
	}
	
	public ScrollHandler getScrollHandler() {
		return scroller;
	}
	
}















