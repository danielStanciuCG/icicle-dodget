package com.turbochargedapps.icicles.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.turbochargedapps.icicles.Constants.*;

public class Player {
    private Vector2 position;
    private Viewport viewport;

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
        renderer.begin(ShapeType.Filled);

        //Head
        renderer.circle(position.x, position.y, PLAYER_HEAD_RADIUS, PLAYER_HEAD_SEGMENTS);
        //Body
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
                position.x + PLAYER_HEAD_RADIUS,0,
                PLAYER_LIMB_WIDTH
        );

        //Left arm
        renderer.rectLine(
                position.x, position.y - PLAYER_HEAD_RADIUS,
                position.x - PLAYER_HEAD_RADIUS,position.y - PLAYER_HEAD_RADIUS * 2,
                PLAYER_LIMB_WIDTH
        );

        //Right arm
        renderer.rectLine(
                position.x, position.y - PLAYER_HEAD_RADIUS,
                position.x + PLAYER_HEAD_RADIUS,position.y - PLAYER_HEAD_RADIUS * 2,
                PLAYER_LIMB_WIDTH
        );
        renderer.end();
    }


}
