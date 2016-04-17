package com.shapeshifter.gui;

import java.awt.event.KeyEvent;

import com.shapeshifter.game.Game;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Font;
import com.shapeshifter.gfx.Screen;

public class RuleScene implements Scene {
	
	private Game game;
	
	public RuleScene(Game game) {
		this.game = game;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderImage(0, 0, Art.parallax);
		
		String line1 = "Space to jump";
		String line2 = "CTRL to be an anvil";
		String line3 = "Contact = die";
		String line4 = "Except if anvil";
		String line5 = "Press ENTER to play";
		
		int middle = screen.getWidth() / 4;
		
		Font.render(line1, middle - line1.length() * 4, 10, screen);
		Font.render(line2, middle - line2.length() * 4, 20, screen);
		Font.render(line3, middle - line3.length() * 4, 30, screen);
		Font.render(line4, middle - line4.length() * 4, 40, screen);
		Font.render(line5, middle - line5.length() * 4, screen.getHeight() / 2 - 20, screen);
	}

	@Override
	public void update() {
		if(Game.INPUT.isKeyDown(KeyEvent.VK_ENTER)) {
			this.game.setScene(new GameScene(this.game));
		}
	}

}
