package com.turbochargedapps.icicles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.turbochargedapps.icicles.Difficulty;
import com.turbochargedapps.icicles.actors.IcicleRain;
import com.turbochargedapps.icicles.actors.Player;

import static com.turbochargedapps.icicles.Constants.*;

public class IciclesScreen implements Screen {
    private ExtendViewport icicleRainViewport;
    private ShapeRenderer renderer;
    private Player player;
    private IcicleRain icicleRain;

    private ScreenViewport hudViewport;
    private SpriteBatch batch;
    private BitmapFont font;
    private int highScore;
    private Difficulty difficulty;

    private long initialTime;

    public IciclesScreen(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        icicleRainViewport = new ExtendViewport(WORLD_SIZE, WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        hudViewport = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        player = new Player(icicleRainViewport);
        icicleRain = new IcicleRain(icicleRainViewport, difficulty);

        highScore = 0;
    }

    @Override
    public void resize(int width, int height) {
        icicleRainViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / HUD_FONT_REFERENCE_SCREEN_SIZE);

        player.init();
        icicleRain.init();
    }

    @Override
    public void render(float delta) {
        icicleRain.update(delta);
        player.update(delta);
        if (player.hitByIcicle(icicleRain)) {
            icicleRain.init();
        }

        icicleRainViewport.apply(true);
        Gdx.gl.glClearColor(BG_COLOUR.r, BG_COLOUR.g, BG_COLOUR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(icicleRainViewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);

        //Draw icicles
        icicleRain.render(renderer);


        //Draw player
        player.render(renderer);

        renderer.end();

        //HUD
        highScore = Math.max(highScore, icicleRain.getIciclesDodged());
        hudViewport.apply(true);
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        //Draw hud
        batch.begin();

        //Difficulty level
        font.draw(
                batch, "DIFFICULTY: " + difficulty.getDifficultyLabel(),
                HUD_MARGIN, hudViewport.getWorldHeight() - HUD_MARGIN
        );

        //Deaths
        font.draw(
                batch, "DEATHS: " + player.getDeaths(),
                HUD_MARGIN, hudViewport.getWorldHeight() - HUD_MARGIN * 2
        );

        //Current score
        font.draw(
                batch, "SCORE: " + icicleRain.getIciclesDodged(),
                hudViewport.getWorldWidth() - HUD_MARGIN, hudViewport.getWorldHeight() - HUD_MARGIN,
                0, Align.right, false
        );

        //Hi Score
        font.draw(
                batch, "HI SCORE: " + highScore,
                hudViewport.getWorldWidth() - HUD_MARGIN, hudViewport.getWorldHeight() - HUD_MARGIN * 2,
                0, Align.right, false
        );

        //FPS counter
        float fps = 1 / delta;

        font.draw(
                batch, "FPS: " + Math.round(fps),
                HUD_MARGIN, HUD_MARGIN
        );

        batch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
