package com.turbochargedapps.icicles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.turbochargedapps.icicles.actors.IcicleRain;
import com.turbochargedapps.icicles.actors.Player;

import static com.turbochargedapps.icicles.Constants.*;

public class IciclesScreen implements Screen {
    private ExtendViewport gameViewport;
    private ShapeRenderer renderer;
    private Player player;
    private IcicleRain icicleRain;

    @Override
    public void show() {
        gameViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        player = new Player(gameViewport);
        icicleRain = new IcicleRain(gameViewport);
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        player.init();
        icicleRain.init();
    }

    @Override
    public void render(float delta) {
        icicleRain.update(delta);
        player.update(delta);
        gameViewport.apply(true);

        Gdx.gl.glClearColor(BG_COLOUR.r, BG_COLOUR.g, BG_COLOUR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(gameViewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);

        //Draw icicles
        icicleRain.render(renderer);


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
