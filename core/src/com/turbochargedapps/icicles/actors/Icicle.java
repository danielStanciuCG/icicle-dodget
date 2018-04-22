package com.turbochargedapps.icicles.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import static com.turbochargedapps.icicles.Constants.*;

/**
 * Blueprint for a single icicle object.
 */
public class Icicle {
    private Vector2 position;

    /**
     * Creates an icicle at a specific position.
     * @param position The required position.
     */
    public Icicle(Vector2 position) {
        this.position = position;
    }

    /**
     * Draws a single icicle to screen
     * @param renderer Requires an instance of ShapeRenderer in order to accomplish the task.
     */
    public void render(ShapeRenderer renderer) {
        renderer.setColor(ICICLE_COLOR);
        renderer.set(ShapeType.Filled);

        renderer.triangle(
                position.x, position.y,
                position.x - ICICLE_WIDTH / 2,position.y + ICICLE_HEIGHT,
                position.x + ICICLE_WIDTH / 2, position.y + ICICLE_HEIGHT
                );
    }

}
