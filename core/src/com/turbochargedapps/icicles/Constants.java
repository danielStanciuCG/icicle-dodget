package com.turbochargedapps.icicles;

import com.badlogic.gdx.graphics.Color;

/**
 * This class contains all the constants needed in the game in order to make tweaking easier.
 * This class also makes adding new features to the game easier.
 */
public class Constants {
    //World
    public static final float WORLD_SIZE = 10.0f;

    //Icicle
    public static final Color BG_COLOUR = Color.BLUE;
    public static final float ICICLE_HEIGHT = 1f;
    public static final float ICICLE_WIDTH = 0.5f;
    public static final Color ICICLE_COLOR = Color.WHITE;

    //Player
    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final float PLAYER_HEAD_SEGMENTS = 20;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final Color PLAYER_COLOR = Color.BLACK;

}
