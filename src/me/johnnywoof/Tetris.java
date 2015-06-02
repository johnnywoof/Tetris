package me.johnnywoof;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {

	public static final Dimension windowDimensions = new Dimension(400, 600);

	public Tetris() {

		super("Tetris");

		GameEngine gameEngine = new GameEngine(this);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);//Someday it'll be true

		this.setLayout(new BorderLayout());

		this.add(new GameRenderer(gameEngine), BorderLayout.CENTER);

		this.getContentPane().setPreferredSize(windowDimensions);

		this.setSize(windowDimensions);

		this.pack();

		this.setLocationRelativeTo(null);

		this.setVisible(true);

		gameEngine.start();

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				new Tetris();

			}
		});

	}

}
