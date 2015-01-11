package com.github.davidthomas426.play2048;

public class UldrStrategy implements Strategy {

	private Move[] moves = new Move[] { Move.UP, Move.LEFT, Move.DOWN, Move.RIGHT };
	private int curr = 0;

	public Move next(Grid grid) {
		Move m = moves[curr];
		curr = (curr + 1) % moves.length;
		return m;
	}
}
