package com.shapeshifter.gui;

import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Font;
import com.shapeshifter.gfx.Screen;

public class WinScene implements Scene {
	
	@Override
	public void render(Screen screen) {
		String twitter = "Follow me @S0orax";
		String win = "You win";
		
		screen.renderImage(0, 0, Art.parallax);
		
		Font.render(win, screen.getWidth() / 4 - win.length() * 4, 10, screen);
		Font.render(twitter, screen.getWidth() / 4 - twitter.length() * 4, 20, screen);
	}

	@Override
	public void update() {
		
	}

}
