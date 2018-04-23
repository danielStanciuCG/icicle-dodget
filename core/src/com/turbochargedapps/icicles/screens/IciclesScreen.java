package com.turbochargedapps.icicles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.turbochargedapps.icicles.actors.Icicle;
import com.turbochargedapps.icicles.actors.Player;

import static com.turbochargedapps.icicles.Constants.*;

public class IciclesScreen implements Screen {
    private ExtendViewport iciclesViewport;
    private ShapeRenderer renderer;
    private Icicle icicle;
    private Player player;

    @Override
    public void show() {
        iciclesViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        icicle = new Icicle(new Vector2(WORLD_SIZE / 2, WORLD_SIZE / 2));
        player = new Player(iciclesViewport);
    }

    @Override
    public void resize(int width, int height) {
        iciclesViewport.update(width, height, true);
        player.init();
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        iciclesViewport.apply(true);

        Gdx.gl.glClearColor(BG_COLOUR.r, BG_COLOUR.g, BG_COLOUR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(iciclesViewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);

        //Draw icicle
        icicle.render(renderer);
        //Draw player
        player.render(renderer);
        renderer.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
