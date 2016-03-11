package com.nyxhemera.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation birdAnimation;
    public static TextureRegion bird, birdDown, birdUp;

    public static TextureRegion skullUp, skullDown, bar;
    
    public static Sound dead;
    public static Sound flap;
    public static Sound coin;

    public static void load() {

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        //the filter handles magnification and minification of textures
        //nearest makes sure the pixel art doesn't get blurry as it scales up
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        //texture region takes a texture, the coordinates of the section of the texture to be used
        //and then the width and height of that specific portion of the texture
        //the flip is due to using the y-down coordinate system
        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);
        
        //pass an animation an array of texture regions
        //each portion of the array will be a frame
        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds); //each frame is 0.06 seconds long
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); //ping pong plays the loop back and forth, down, middle, up, middle, down, etc.

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
        
        dead =  Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }
}
