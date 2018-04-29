package com.turbochargedapps.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

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

    //Player features
    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final float PLAYER_BODY_LENGTH = PLAYER_HEAD_RADIUS * 2;
    public static final float PLAYER_LIMB_LENGTH = PLAYER_BODY_LENGTH / 2;
    public static final Color PLAYER_COLOR = Color.BLACK;

    //Player movement
    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;
    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    //IcicleRain movement
    public static final Vector2 ICICLES_ACCELERATION = new Vector2(0, -5.0f);

    //HUD
    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
    public static final float HUD_MARGIN = 20.0f;

    //Difficulty screen
    public static final String COLD_DIFFICULTY_LABEL = "COLD";
    public static final int COLD_ICICLE_SPAWNS_PER_SECOND = 5;

    public static final String COLDER_DIFFICULTY_LABEL = "COLDER";
    public static final int COLDER_ICICLE_SPAWNS_PER_SECOND = 15;

    public static final String COLDEST_DIFFICULTY_LABEL = "COLDEST";
    public static final int COLDEST_ICICLE_SPAWNS_PER_SECOND = 25;
}
