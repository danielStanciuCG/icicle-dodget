package com.turbochargedapps.icicles.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.turbochargedapps.icicles.Constants.*;

public class Icicles {
    private Array<Icicle> icicleRain;
    private Viewport viewport;

    public Icicles(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    /**
     * Sets the initial position
     */
    public void init() {
        icicleRain = new Array<Icicle>(false, 100);
    }

    /**
     * Updates the position and state of icicles on screen
     *
     * @param delta Time since last frame
     */
    public void update(float delta) {
        //Add a new icicle at the top of the viewport at a random x position
        if (MathUtils.random() < delta * ICICLE_SPAWNS_PER_SECOND) {
            Icicle newIcicle = new Icicle(new Vector2(
                    viewport.getWorldWidth() * MathUtils.random(),
                    viewport.getWorldHeight()
            ));
            icicleRain.add(newIcicle);
        }

        //Update each icicle
        for (Icicle icicle : icicleRain) {
            icicle.update(delta);
        }
    }

    /**
     * Renders the icicle rain on screen
     * @param renderer ShapeRenderer object
     */
    public void render(ShapeRenderer renderer) {
        renderer.setColor(ICICLE_COLOR);

        for (Icicle icicle : icicleRain) {
            icicle.render(renderer);
        }
    }
}
