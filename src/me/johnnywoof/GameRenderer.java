package me.johnnywoof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameRenderer extends JPanel {

	public static int boxWidth = (Tetris.windowDimensions.width / 10),
			boxHeight = (Tetris.windowDimensions.height / 20);

	private final GameEngine gameEngine;

	public GameRenderer(final GameEngine gameEngine) {

		this.gameEngine = gameEngine;

		Action leftKeyPressed = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameEngine.moveLeftCount++;

			}

		};

		Action rightKeyPressed = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gameEngine.moveRightCount++;

			}

		};

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
				"leftKeyPressed");
		this.getActionMap().put("leftKeyPressed",
				leftKeyPressed);

		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
				"rightKeyPressed");
		this.getActionMap().put("rightKeyPressed",
				rightKeyPressed);

	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D g = (Graphics2D) graphics.create();

		g.drawString(String.valueOf(this.gameEngine.random.nextInt(10)), 5, 10);

		int recX = 0, recY = 0;
		int sideCount = 0;

		g.setColor(Color.GRAY);

		for (int i = 0; i < this.gameEngine.tiles.length; i++) {

			g.drawRect(recX, recY, boxWidth, boxHeight);

			g.drawString(String.valueOf(i), recX + 10, recY + 15);

			if (this.gameEngine.tiles[i]) {

				g.setColor(Color.BLACK);

				g.fillRect(recX, recY, boxWidth, boxHeight);

				g.setColor(Color.GRAY);

			}

			sideCount++;

			if (sideCount % 10 == 0) {

				recX = 0;
				recY += boxHeight;

			} else {

				recX += boxWidth;

			}

		}

		g.dispose();

	}

}
