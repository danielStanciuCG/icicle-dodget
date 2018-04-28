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
    private Vector2 velocity;

    /**
     * Creates an icicle at a specific position.
     * @param position The required position.
     */
    public Icicle(Vector2 position) {
        this.position = position;
        velocity = new Vector2();
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
        renderer.end();
    }

    /**
     * Updates the position and state of the icicle
     * @param delta Time since last frame
     */
    public void update(float delta) {
        velocity.mulAdd(ICICLES_ACCELERATION, delta);
        position.mulAdd(velocity, delta);

        /*
        The two lines of code above is the equivalent of:

        velocity.x += delta * ICICLES_ACCELERATION.x;
        velocity.y += delta * ICICLES_ACCELERATION.y;
        position.x += delta * velocity.x;
        position.y += delta * velocity.y;

        */
    }

}
