package com.github.davidthomas426.play2048;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
	public static void main(String[] args) {
		Engine engine = new SeleniumEngine(new FirefoxDriver());
		Strategy strategy = new UldrStrategy();
		Game game = new Game(engine, strategy);
		game.run();
	}
}
