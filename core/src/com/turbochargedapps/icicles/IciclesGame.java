package com.turbochargedapps.icicles;

import com.badlogic.gdx.Game;
import com.turbochargedapps.icicles.screens.IciclesScreen;

public class IciclesGame extends Game {
	
	@Override
	public void create () {
		setScreen(new IciclesScreen(Difficulty.COLD));
	}
}
