package com.github.davidthomas426.play2048;

public interface Engine {
	Grid getGrid();
	int getScore();
	void move(Move move);
	boolean isOver();
	void quit();
}
