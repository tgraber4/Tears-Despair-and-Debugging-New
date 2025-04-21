package hw4.maze.classes;

import java.util.ArrayList;

public class Row {
	ArrayList<Cell> cells;

	/**
	 * Takes in a list of Cell objects and returns the created Row object.
	 * @param cells
	 */
	public Row(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
	
	/**
	 * Converts a Row into string format.
	 */
	@Override
	public String toString() {
		String temp = "Row [cells=[";
		int size = this.cells.size();
		int counter = 0;
		for (Cell item : this.cells) {
			temp+= item.toString();
			counter++;
			if (counter < size) {
				temp += ", ";
			}
		}
		temp += "]]";
		return temp;
	}
	
	
}
