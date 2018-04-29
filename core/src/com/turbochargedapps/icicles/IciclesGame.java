package com.turbochargedapps.icicles;

import com.badlogic.gdx.Game;
import com.turbochargedapps.icicles.screens.DifficultyScreen;
import com.turbochargedapps.icicles.screens.IciclesScreen;

public class IciclesGame extends Game {
	
	@Override
	public void create () {
		showDifficultyScreen();
	}

	/**
	 * Sets the screen to DifficultyScreen
	 */
	public void showDifficultyScreen() {
		setScreen(new DifficultyScreen(this));
	}

	/**
	 * Sets the screen to DifficultyScreen
	 */
	public void showIciclesScreen(Difficulty difficulty) {
		setScreen(new IciclesScreen(this, difficulty));
	}
}
