package com.turbochargedapps.icicles.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.turbochargedapps.icicles.Constants.*;

public class Player {
    private Vector2 position;
    private Viewport viewport;
    private int deaths;

    /**
     * The constructor sets the viewport and calls a method that creates the model.
     *
     * @param viewport Viewport to be used.
     */
    public Player(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    /**
     * Sets the initial position
     */
    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, PLAYER_HEAD_HEIGHT);
    }

    /**
     * Draws the player to screen.
     *
     * @param renderer Requires an instance of ShapeRenderer in order to accomplish the task.
     */
    public void render(ShapeRenderer renderer) {
        renderer.setColor(PLAYER_COLOR);

        //Head
        renderer.circle(position.x, position.y, PLAYER_HEAD_RADIUS, PLAYER_HEAD_SEGMENTS);

        //Left eye
        renderer.setColor(Color.WHITE);
        renderer.circle(
                position.x - PLAYER_HEAD_RADIUS / 3, position.y + PLAYER_HEAD_RADIUS / 4,
                PLAYER_HEAD_RADIUS / 8, PLAYER_HEAD_SEGMENTS
        );

        //Right eye
        renderer.circle(
                position.x + PLAYER_HEAD_RADIUS / 3, position.y + PLAYER_HEAD_RADIUS / 4,
                PLAYER_HEAD_RADIUS / 8, PLAYER_HEAD_SEGMENTS
        );

        //Mouth
        renderer.arc(
                position.x, position.y - PLAYER_HEAD_RADIUS / 5,
                PLAYER_HEAD_RADIUS / 2,
                180, 180, PLAYER_HEAD_SEGMENTS
        );
        renderer.setColor(Color.BLACK);
        renderer.arc(
                position.x, position.y - PLAYER_HEAD_RADIUS / 5,
                PLAYER_HEAD_RADIUS / 2.5f,
                180, 180, PLAYER_HEAD_SEGMENTS
        );

        //Nose
        renderer.setColor(Color.WHITE);
        renderer.rectLine(
                position.x, position.y,
                position.x, position.y - PLAYER_HEAD_RADIUS / 3,
                PLAYER_LIMB_WIDTH / 4
        );

        //Body
        renderer.setColor(Color.BLACK);
        renderer.rectLine(
                position.x, position.y - PLAYER_HEAD_RADIUS,
                position.x, PLAYER_LIMB_LENGTH,
                PLAYER_LIMB_WIDTH
        );
        //Left leg
        renderer.rectLine(
                position.x, PLAYER_LIMB_LENGTH,
                position.x - PLAYER_HEAD_RADIUS,
                0, PLAYER_LIMB_WIDTH

        );
        //Right leg
        renderer.rectLine(
                position.x, PLAYER_LIMB_LENGTH,
                position.x + PLAYER_HEAD_RADIUS, 0,
                PLAYER_LIMB_WIDTH
        );

        //Left arm
        renderer.rectLine(
                position.x, position.y - PLAYER_HEAD_RADIUS,
                position.x - PLAYER_HEAD_RADIUS, position.y - PLAYER_HEAD_RADIUS * 2,
                PLAYER_LIMB_WIDTH
        );

        //Right arm
        renderer.rectLine(
                position.x, position.y - PLAYER_HEAD_RADIUS,
                position.x + PLAYER_HEAD_RADIUS, position.y - PLAYER_HEAD_RADIUS * 2,
                PLAYER_LIMB_WIDTH
        );
    }

    /**
     * Updates the position and state of the player
     *
     * @param delta Time since last frame
     */
    public void update(float delta) {
        //Move to the right (keyboard)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * PLAYER_MOVEMENT_SPEED;
        }

        //Move to the right (keyboard)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * PLAYER_MOVEMENT_SPEED;
        }

        float accelerometerInput = Gdx.input.getAccelerometerY() / (GRAVITATIONAL_ACCELERATION * ACCELEROMETER_SENSITIVITY);
        position.x += delta * accelerometerInput * PLAYER_MOVEMENT_SPEED;

        stayInBounds();
    }

    /**
     * Ensure the player stays within the viewport
     */
    private void stayInBounds() {
        if (position.x < PLAYER_HEAD_RADIUS) {
            position.x = PLAYER_HEAD_RADIUS;
        }
        if (position.x > viewport.getWorldWidth() - PLAYER_HEAD_RADIUS) {
            position.x = viewport.getWorldWidth() - PLAYER_HEAD_RADIUS;
        }
    }

    /**
     * Checks if an icicle is within the player's head
     *
     * @param icicleRain IcicleRain, which is an array of Icicle objects
     * @return boolean
     */
    public boolean hitByIcicle(IcicleRain icicleRain) {
        boolean isHit = false;

        for (Icicle icicle : icicleRain.getIcicles()) {
            if (icicle.getPosition().dst(position) < PLAYER_HEAD_RADIUS) {
                isHit = true;
                deaths++;
            }
        }
        return isHit;
    }

    /**
     * Returns the number of times the player has been hit in the head by an icicle.
     * @return int
     */
    public int getDeaths() {
        return deaths;
    }
}
