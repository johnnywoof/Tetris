package me.johnnywoof;

import java.util.TimerTask;

public class GameTickTask extends TimerTask {

	private final GameEngine gameEngine;

	public GameTickTask(GameEngine gameEngine) {

		this.gameEngine = gameEngine;

	}

	@Override
	public void run() {

		this.gameEngine.tick();

	}

}
