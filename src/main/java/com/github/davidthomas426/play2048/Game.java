package com.github.davidthomas426.play2048;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Game {

	private Engine engine;
	private Strategy strategy;

	public static void main(String[] args) throws InterruptedException {
		Engine engine = new SeleniumEngine(new FirefoxDriver());
		Strategy strategy = new UldrStrategy();
		Game game = new Game(engine, strategy);
		game.run();
	}

	public Game(Engine engine, Strategy strategy) {
		this.strategy = strategy;
		this.engine = engine;
	}

	public void run() throws InterruptedException {
		while (!engine.isOver()) {
			Grid grid = engine.getGrid();
			printGrid(grid);
			System.out.println();

			Move nextMove = strategy.next();
			engine.move(nextMove);
		}

		Grid grid = engine.getGrid();
		printGrid(grid);
	}

	private void printGrid(Grid grid) {
		System.out.print(grid.ascii());
		System.out.println("Score: " + engine.getScore());
	}
}
