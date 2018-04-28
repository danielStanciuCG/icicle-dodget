package com.turbochargedapps.icicles.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.turbochargedapps.icicles.Constants.*;

public class IcicleRain {
    private DelayedRemovalArray<Icicle> icicleRain;
    private Viewport viewport;

    public IcicleRain(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    /**
     * Sets the initial position
     */
    public void init() {
        icicleRain = new DelayedRemovalArray<Icicle>(false, 100);
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

        //Begin removal session
        icicleRain.begin();

        //Remove icicles completely off the bottom of the screen
        for (int i = 0; i < icicleRain.size; i++) {
            if (icicleRain.get(i).getPosition().y < -ICICLE_HEIGHT) {
                icicleRain.removeIndex(i);
            }
        }

        //End removal session
        icicleRain.end();
    }

    /**
     * Renders the icicle rain on screen
     *
     * @param renderer ShapeRenderer object
     */
    public void render(ShapeRenderer renderer) {
        renderer.setColor(ICICLE_COLOR);

        for (Icicle icicle : icicleRain) {
            icicle.render(renderer);
        }
    }
}
