package com.turbochargedapps.icicles.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.turbochargedapps.icicles.Difficulty;

import static com.turbochargedapps.icicles.Constants.*;

public class IcicleRain {
    private DelayedRemovalArray<Icicle> icicles;
    private Viewport viewport;
    private int iciclesDodged;
    private Difficulty difficulty;

    public IcicleRain(Viewport viewport, Difficulty difficulty) {
        this.viewport = viewport;
        this.difficulty = difficulty;
        init();
    }

    /**
     * Sets the initial position
     */
    public void init() {
        icicles = new DelayedRemovalArray<Icicle>(false, 100);
        iciclesDodged = 0;
    }

    /**
     * Updates the position and state of icicles on screen
     *
     * @param delta Time since last frame
     */
    public void update(float delta) {
        //Add a new icicle at the top of the viewport at a random x position
        if (MathUtils.random() < delta * difficulty.getIcicleSpawnRate()) {
            Icicle newIcicle = new Icicle(new Vector2(
                    viewport.getWorldWidth() * MathUtils.random(),
                    viewport.getWorldHeight()
            ));
            icicles.add(newIcicle);
        }

        //Update each icicle
        for (Icicle icicle : icicles) {
            icicle.update(delta);
        }

        //Begin removal session
        icicles.begin();

        //Remove icicles completely off the bottom of the screen
        for (int i = 0; i < icicles.size; i++) {
            if (icicles.get(i).getPosition().y < -ICICLE_HEIGHT) {
                icicles.removeIndex(i);
                iciclesDodged++;
            }
        }

        //End removal session
        icicles.end();
    }

    /**
     * Renders the icicle rain on screen
     *
     * @param renderer ShapeRenderer object
     */
    public void render(ShapeRenderer renderer) {
        renderer.setColor(ICICLE_COLOR);

        for (Icicle icicle : icicles) {
            icicle.render(renderer);
        }
    }

    /**
     * Returns the array of icicles.
     * @return DelayedRemovalArray
     */
    public DelayedRemovalArray<Icicle> getIcicles() {
        return icicles;
    }

    /**
     * Returns the number of icicles that have gone completely off the bottom of the screen
     * @return int
     */
    public int getIciclesDodged() {
        return iciclesDodged;
    }
}
