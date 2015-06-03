package me.johnnywoof;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Timer;

public class GameEngine {

	public int moveLeftCount = 0;
	public int moveRightCount = 0;

	public final boolean[] tiles = new boolean[200];
	public final Tetris tetris;
	public final SecureRandom random = new SecureRandom();

	public final boolean[] fallingTiles = new boolean[200];

	private final Timer timer = new Timer();

	public GameEngine(Tetris tetris) {

		Arrays.fill(this.tiles, false);
		Arrays.fill(this.fallingTiles, false);

		this.tetris = tetris;

	}

	public void start() {

		this.timer.scheduleAtFixedRate(new GameTickTask(this), 0, 200);

	}

	public void stopTicking() {

		this.timer.cancel();
		this.timer.purge();

	}

	public void tick() {

		boolean anyFalling = false;

		boolean deleteAllFalling = false;

		for (int i = this.fallingTiles.length - 11; i >= 0; i--) {

			if (this.fallingTiles[i]) {

				//System.out.println(i);

				int nextIndex = (i + 10);

				nextIndex -= this.moveLeftCount;
				nextIndex += this.moveRightCount;

				if (this.tiles[nextIndex] && !this.fallingTiles[nextIndex]) {//Block already exists below us.

					deleteAllFalling = true;
					break;

				}

			}

		}

		if (deleteAllFalling) {

			Arrays.fill(this.fallingTiles, false);
			anyFalling = false;

		} else {

			for (int i = this.fallingTiles.length - 11; i >= 0; i--) {

				if (this.fallingTiles[i]) {

					//System.out.println(i);

					int nextIndex = (i + 10);

					nextIndex -= this.moveLeftCount;
					nextIndex += this.moveRightCount;

					if (this.tiles[nextIndex]) {//Block already exists below us.

						this.fallingTiles[i] = false;

						this.tiles[i] = true;

					} else {

						this.fallingTiles[i] = false;
						this.tiles[i] = false;

						this.fallingTiles[nextIndex] = true;
						this.tiles[nextIndex] = true;

						anyFalling = true;

					}

				}

			}

		}

		this.moveLeftCount = 0;
		this.moveRightCount = 0;

		if (!anyFalling) {

			switch (this.random.nextInt(4)) {

				case 0:

					//Tri-piece thingy
					this.fallingTiles[4] = true;
					this.fallingTiles[14] = true;
					this.fallingTiles[24] = true;
					this.fallingTiles[34] = true;

					break;

				case 1:

					//Long thingy
					this.fallingTiles[4] = true;
					this.fallingTiles[13] = true;
					this.fallingTiles[14] = true;
					this.fallingTiles[15] = true;

					break;

				case 2:

					//L-shape
					this.fallingTiles[4] = true;
					this.fallingTiles[14] = true;
					this.fallingTiles[24] = true;
					this.fallingTiles[25] = true;

					break;
				default:

					//Square
					this.fallingTiles[4] = true;
					this.fallingTiles[5] = true;
					this.fallingTiles[14] = true;
					this.fallingTiles[15] = true;
					break;

			}

		}

		this.tetris.repaint();

	}

}
