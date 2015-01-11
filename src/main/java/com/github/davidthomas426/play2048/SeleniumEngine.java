package com.github.davidthomas426.play2048;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumEngine implements Engine {

	private WebDriver driver;
	private WebElement body;
	private WebElement scoreContainer;
	private WebElement gameMessage;

	private String url = "http://gabrielecirulli.github.io/2048/";
	private Pattern tileNumPattern = Pattern.compile("tile-(\\d+)");
	private Pattern tilePosPattern = Pattern
			.compile("tile-position-(\\d+)-(\\d+)");
	private Map<Move, Keys> keyMap;

	private static int defaultDelayMillis = 300;
	private int delayMillis;

	public SeleniumEngine(WebDriver driver, int delayMillis) {
		this.driver = driver;
		this.delayMillis = delayMillis;

		driver.get(url);
		body = driver.findElement(By.tagName("body"));
		scoreContainer = driver.findElement(By.className("score-container"));
		gameMessage = driver.findElement(By.className("game-message"));
		setupKeyMap();
	}

	public SeleniumEngine(WebDriver driver) {
		this(driver, defaultDelayMillis);
	}

	private void setupKeyMap() {
		keyMap = new HashMap<Move, Keys>();
		keyMap.put(Move.UP, Keys.ARROW_UP);
		keyMap.put(Move.DOWN, Keys.ARROW_DOWN);
		keyMap.put(Move.LEFT, Keys.ARROW_LEFT);
		keyMap.put(Move.RIGHT, Keys.ARROW_RIGHT);
	}

	@Override
	public Grid getGrid() {
		Grid grid = new Grid(4);
		List<WebElement> tiles = driver.findElements(By.className("tile"));
		for (WebElement tile : tiles) {
			String classNames = tile.getAttribute("class");

			Matcher matcher = tileNumPattern.matcher(classNames);
			matcher.find();
			int val = Integer.parseInt(matcher.group(1));

			matcher = tilePosPattern.matcher(classNames);
			matcher.find();
			int y = Integer.parseInt(matcher.group(1));
			int x = Integer.parseInt(matcher.group(2));

			grid.setValue(x, y, val);
		}
		return grid;
	}

	@Override
	public int getScore() {
		String scoreText = scoreContainer.getText().split("\\s+")[0];
		int score = Integer.parseInt(scoreText);
		return score;
	}

	@Override
	public void move(Move move) {
		body.sendKeys(keyMap.get(move));
		
		// Delay to give the game a chance to process the move.
		try {
			Thread.sleep(delayMillis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}

	@Override
	public boolean isOver() {
		return gameMessage.getAttribute("class").contains("game-over");
	}

	@Override
	public void quit() {
		driver.quit();
	}

}
