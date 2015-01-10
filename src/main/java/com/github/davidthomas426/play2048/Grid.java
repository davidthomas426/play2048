package com.github.davidthomas426.play2048;

import java.util.Arrays;

public class Grid {
	private int[][] cells;
	private static String sep = System.getProperty("line.separator");

	public Grid(int size) {
		this.cells = new int[size][size];
	}

	public int getSize() {
		return cells.length;
	}
	
	public int getValue(int x, int y) {
		try {
			return cells[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid cell position");
		}
	}

	public void setValue(int x, int y, int v) {
		try {
			cells[x-1][y-1] = v;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Invalid cell position");
		}
	}

	public void clear() {
		for (int[] row : cells) {
			Arrays.fill(row, 0);
		}
	}
	
	public String ascii() {
		StringBuilder sb = new StringBuilder();
		sb.append("+--------+--------+--------+--------+").append(sep);
		for (int i = 0; i < getSize(); i++) {
			sb.append("|        |        |        |        |").append(sep);
			sb.append("|");
			for (int j = 0; j < getSize(); j++) {
				int val = getValue(i, j);
				if (val > 0) {
					sb.append(String.format("%7d |", val));
				} else {
					sb.append("        |");
				}
			}
			sb.append(sep);
			sb.append("|        |        |        |        |").append(sep);
			sb.append("+--------+--------+--------+--------+").append(sep);
		}
		return sb.toString();
	}
}
