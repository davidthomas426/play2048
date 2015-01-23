package com.github.davidthomas426.play2048.ai;

import com.datumbox.opensource.ai.AIsolver;
import com.datumbox.opensource.dataobjects.Direction;
import com.datumbox.opensource.game.Board;
import com.github.davidthomas426.play2048.Grid;
import com.github.davidthomas426.play2048.Move;
import com.github.davidthomas426.play2048.Strategy;

public class AIStrategy implements Strategy {

	private int depth = 5;

	@Override
	public Move next(Grid grid) {
		Board board = new Board();
		board.setBoardArray(grid.getCellArray());
		Direction hint = null;
		try {
			hint = AIsolver.findBestMove(board, depth);
		} catch (CloneNotSupportedException e) {
			System.err.println("Wha?!");
		}
		switch (hint) {
		case UP:
			return Move.UP;
		case DOWN:
			return Move.DOWN;
		case LEFT:
			return Move.LEFT;
		case RIGHT:
			return Move.RIGHT;
		}
		return null;
	}

}
