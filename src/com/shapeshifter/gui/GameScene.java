package com.shapeshifter.gui;

import java.awt.event.KeyEvent;

import com.shapeshifter.audio.Sound;
import com.shapeshifter.game.Game;
import com.shapeshifter.gfx.Font;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.Level;

public class GameScene implements Scene {

	private Level level;
	private Game game;
	
	public GameScene(Game game) {
		this.level = new Level("/levels/test.png");
		this.game = game;
		Sound.music.loop();
	}
	
	@Override
	public void render(Screen screen) {
		this.level.render(screen);
		if(this.level.isLoosing()) {
			String message = "Game over";
			String enter = "Press enter to replay";
			Font.render(message, screen.getWidth() / 4 - message.length() * 4, 10, screen);
			Font.render(enter, screen.getWidth() / 4 - enter.length() * 4, 20, screen);
		}
	}

	@Override
	public void update() {
		this.level.update();
		if(this.level.isLoosing()) {
			if(Game.INPUT.isKeyDown(KeyEvent.VK_ENTER)) {
				this.level = new Level(this.level.getFileName());
			}
		}
		
		if(this.level.getWin() && this.level.isFinished()) {
			this.game.setScene(new WinScene());
			return;
		}
		
		if(this.level.getWin()) {
			this.level = new Level(this.level.nextLevel());
		}
	}

}
