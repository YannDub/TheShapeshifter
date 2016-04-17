package com.shapeshifter.gui;

import java.awt.event.KeyEvent;

import com.shapeshifter.game.Game;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Font;
import com.shapeshifter.gfx.Screen;

public class MainScene implements Scene {
	
	private Game game;
	
	public MainScene(Game game) {
		this.game = game;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderImage(0, 0, Art.parallax);
		screen.renderImage(screen.getWidth() / 4 - Art.title.getWidth() / 2, 10, Art.title);
		Font.render("Press Enter to play", screen.getWidth() / 4 - 20 * 4, screen.getHeight() / 4 + 8, screen);
		Font.render("Press R to see rule", screen.getWidth() / 4 - 20 * 4, screen.getHeight() / 4 + 20, screen);
	}

	@Override
	public void update() {
		if(Game.INPUT.isKeyDown(KeyEvent.VK_ENTER)) {
			this.game.setScene(new GameScene(this.game));
		}
		else if(Game.INPUT.isKeyDown(KeyEvent.VK_R)) {
			this.game.setScene(new RuleScene(this.game));
		}
	}

}
