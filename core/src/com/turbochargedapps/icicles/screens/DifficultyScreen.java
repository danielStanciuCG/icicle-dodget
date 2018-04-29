package com.turbochargedapps.icicles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.turbochargedapps.icicles.Constants.*;

import com.turbochargedapps.icicles.Difficulty;
import com.turbochargedapps.icicles.IciclesGame;

public class DifficultyScreen extends InputAdapter implements Screen {
    private IciclesGame game;
    private FitViewport viewport;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;


    public DifficultyScreen(IciclesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(DIFFICULTY_WORLD_SIZE, DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        font = new BitmapFont();
        renderer = new ShapeRenderer();

        font.getData().setScale(DIFFICULTY_FONT_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        update();
        Gdx.gl.glClearColor(BG_COLOUR.r, BG_COLOUR.g, BG_COLOUR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);

        //Draw left-hand button
        renderer.setColor(COLD_COLOR);
        renderer.circle(
                COLD_DIFFICULTY_POSITION.x, COLD_DIFFICULTY_POSITION.y,
                DIFFICULTY_BUTTON_RADIUS, PLAYER_HEAD_SEGMENTS
        );

        //Draw medium button
        renderer.setColor(COLDER_COLOR);
        renderer.circle(
                COLDER_DIFFICULTY_POSITION.x, COLDER_DIFFICULTY_POSITION.y,
                DIFFICULTY_BUTTON_RADIUS, PLAYER_HEAD_SEGMENTS
        );

        //Draw right-hand button
        renderer.setColor(COLDEST_COLOR);
        renderer.circle(
                COLDEST_DIFFICULTY_POSITION.x, COLDEST_DIFFICULTY_POSITION.y,
                DIFFICULTY_BUTTON_RADIUS, PLAYER_HEAD_SEGMENTS
        );

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        //Instructions
        font.getData().setScale(DIFFICULTY_FONT_SCALE / 2);
        font.draw(
                batch,
            "Move left: LEFT ARROW (desktop) / TILT PHONE LEFT (mobile)\n" +
                "Move right: RIGHT ARROW (desktop) / TILT PHONE RIGHT (mobile)\n" +
                "Quit / Return to menu: ESC (desktop) / BACK (mobile)\n" +
                "Choose difficulty below to start the game. Have fun!",
                COLD_DIFFICULTY_POSITION.x, COLD_DIFFICULTY_POSITION.y * 1.8f,
                0, Align.left, false
        );

        font.getData().setScale(DIFFICULTY_FONT_SCALE);
        //Draw the font for the left-hand button
        final GlyphLayout coldLayout = new GlyphLayout(font, COLD_DIFFICULTY_LABEL);
        font.draw(
                batch, COLD_DIFFICULTY_LABEL,
                COLD_DIFFICULTY_POSITION.x, COLD_DIFFICULTY_POSITION.y + coldLayout.height / 2,
                0, Align.center, false
        );

        //Draw the font for the middle button
        final GlyphLayout colderLayout = new GlyphLayout(font, COLDER_DIFFICULTY_LABEL);
        font.draw(
                batch, COLDER_DIFFICULTY_LABEL,
                COLDER_DIFFICULTY_POSITION.x, COLDER_DIFFICULTY_POSITION.y + colderLayout.height / 2,
                0, Align.center, false
        );

        //Draw the font for the right-hand button
        final GlyphLayout coldestLayout = new GlyphLayout(font, COLDEST_DIFFICULTY_LABEL);
        font.draw(
                batch, COLDEST_DIFFICULTY_LABEL,
                COLDEST_DIFFICULTY_POSITION.x, COLDEST_DIFFICULTY_POSITION.y + coldestLayout.height / 2,
                0, Align.center, false
        );

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        font.dispose();
        batch.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));

        //Left hand button
        if (worldClick.dst(COLD_DIFFICULTY_POSITION) < DIFFICULTY_BUTTON_RADIUS) {
            game.showIciclesScreen(Difficulty.COLD);
        }

        //Middle button
        if (worldClick.dst(COLDER_DIFFICULTY_POSITION) < DIFFICULTY_BUTTON_RADIUS) {
            game.showIciclesScreen(Difficulty.COLDER);
        }

        //Right-hand button
        if (worldClick.dst(COLDEST_DIFFICULTY_POSITION) < DIFFICULTY_BUTTON_RADIUS) {
            game.showIciclesScreen(Difficulty.COLDEST);
        }

        return true;
    }

    /**
     * Key input.
     */
    private void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }
    }
}
