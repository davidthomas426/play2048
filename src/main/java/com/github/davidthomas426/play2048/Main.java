package com.github.davidthomas426.play2048;

import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.davidthomas426.play2048.ai.AIStrategy;

public class Main {
	public static void main(String[] args) {
		Engine engine = new SeleniumEngine(new FirefoxDriver(), 200);
		Strategy strategy = new AIStrategy();
		Game game = new Game(engine, strategy);
		game.run();
	}
}
