package hw4.maze.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grid {
	ArrayList<Row> rows;

	/**
	 * Grid constructor that takes in a list of Row objects
	 * @param rows
	 */
	public Grid(ArrayList<Row> rows) {
		this.rows = rows;
	}
	
	/**
	 * Grid constructor that takes in no parameters and randomly creates a grid.
	 */
	public Grid() {
		Random rand = new Random();
		int randomInt = rand.nextInt(5) + 3;
		this.generateRandomMaze(randomInt);
	}
	
	/**
	 * Grid constructor that takes in the size of the grid and randomly creates a grid
	 * using that.
	 * @param randomInt
	 */
	public Grid(int randomInt) {
		this.generateRandomMaze(randomInt);
	}
	
	
	
	/**
	 * Takes in a current tile in integer form and the current visited list. Then
	 * determines if adjacent tiles are valid or not. Returns a list of tiles in integer
	 * format that are valid.
	 * @param input
	 * @param visited
	 * @return ArrayList<Integer> 
	 */
	private ArrayList<Integer> getAdjacentTiles(int input, ArrayList<Integer> visited) {
		ArrayList<Integer> tempTileList = new ArrayList<Integer>();
		int rowSize = this.getRows().get(0).getCells().size();
		int temp = input % rowSize;
		if (temp != 0 && !visited.contains(input - 1)) { // can get left
			tempTileList.add(input - 1);
		}
		if (((temp + 1) != rowSize) && !visited.contains(input + 1)) { // can get right
			tempTileList.add(input + 1);
		}
		if (((input + rowSize - rowSize * rowSize) < 0) && !visited.contains(input + rowSize)) { // can go down
			tempTileList.add(input + rowSize);
		}
		if (((input + 1 - rowSize) > 0) && !visited.contains(input - rowSize)) { // can go up
			tempTileList.add(input - rowSize);
		}
		Collections.shuffle(tempTileList);
		return tempTileList;
	}
	
	/**
	 * Converts an integer position into the Cell object in the corresponding
	 * spot in the grid.
	 * @param input
	 * @return
	 */
	private Cell positionToCell (int input) {
		int rowSize = this.getRows().get(0).getCells().size();
		int rowInInput = input / rowSize;
		int cellPositionInInput = input - (input / rowSize) * rowSize;
		return this.getRows().get(rowInInput).getCells().get(cellPositionInInput);
	}
	
	/**
	 * Clears the walls between two inputed tiles.
	 * @param currentTile
	 * @param previousTile
	 */
	private void clearWall(int currentTile, int previousTile) {
		int rowSize = this.getRows().get(0).getCells().size();
		Cell currentCell = positionToCell(currentTile);
		Cell previousCell = positionToCell(previousTile);
		if (currentTile - 1 == previousTile) { // current is right of previous
			currentCell.setLeft(CellComponents.APERTURE);
			previousCell.setRight(CellComponents.APERTURE);
		} else if (currentTile + 1 == previousTile) { // current is left of previous
			currentCell.setRight(CellComponents.APERTURE);
			previousCell.setLeft(CellComponents.APERTURE);
		} else if (currentTile - rowSize == previousTile) { // current is under previous
			currentCell.setUp(CellComponents.APERTURE);
			previousCell.setDown(CellComponents.APERTURE);
		} else if (currentTile + rowSize == previousTile) { // current is above previous
			currentCell.setDown(CellComponents.APERTURE);
			previousCell.setUp(CellComponents.APERTURE);
		}
	}
	
	/**
	 * Generates a random grid using the given grid size.
	 * @param randomInt
	 */
	private void generateRandomMaze(int randomInt) {
		Random rand = new Random();
		int randomExitInt = rand.nextInt(randomInt);
		this.rows = new ArrayList<Row>();
		for (int i = 0; i < randomInt; i++) {
			ArrayList<Cell> cells = new ArrayList<Cell>();
			for (int j = 0; j < randomInt; j++) {
				if (i == randomExitInt && j == 0) {
					cells.add(new Cell(CellComponents.EXIT, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL));
				} else {
					cells.add(new Cell(CellComponents.WALL, CellComponents.WALL, CellComponents.WALL, CellComponents.WALL));
				}
			}
			Row temp = new Row(cells);
			this.rows.add(temp);
		}
		
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<Integer> previous = new ArrayList<Integer>();
		ArrayList<Integer> stack = new ArrayList<Integer>();
		int rowSize = this.getRows().get(0).getCells().size();
		int startTile = rowSize * rowSize - 1;
		ArrayList<Integer> tileList = getAdjacentTiles(startTile, visited);
		for (int item : tileList) {
			stack.add(item);
			previous.add(startTile);
			visited.add(item);
		}
		while (stack.size() > 0) {
			int currentTile = stack.remove(stack.size() - 1);
			int previousTile = previous.remove(previous.size() - 1);
			clearWall(currentTile, previousTile);
			
			tileList.clear();
			tileList = getAdjacentTiles(currentTile, visited);
			for (int item : tileList) {
				stack.add(item);
				previous.add(currentTile);
				visited.add(item);
			}
		}
	}
	
	/**
	 * Testing function
	 */
	public void printGrid() {
		int rowCounter = 0;
		int rowSize = this.getRows().size();
		for (Row row : this.getRows()) {
			String line1 = "";
			String line2 = "";
			String line3 = "";
			int cellCounter = 0;
			int cellSize = row.getCells().size();
			for (Cell cell : row.getCells()) {
				line1 += "#";
				if (cell.getUp().name() == "WALL") {
					line1 += "#";
				} else {
					line1 += " ";
				}
				
				if (cell.getLeft().name() == "WALL") {
					line2 += "#";
				} else {
					line2 += " ";
				}
				
				if (cell.getUp().name() == "EXIT" || cell.getDown().name() == "EXIT" || cell.getRight().name() == "EXIT" || cell.getLeft().name() == "EXIT") {
					line2 += "E";
				} else {
					line2 += "A";
				}
				
				// end of row
				if ((cellCounter + 1) == cellSize) {
					line1 += "#";
					if (cell.getRight().name() == "WALL") {
						line2 += "#";
					} else {
						line2 += " ";
					}
				}
				
				// last row in grid
				if ((rowCounter + 1) == rowSize) {
					line3 += "#";
					if (cell.getDown().name() == "WALL") {
						line3 += "#";
					} else {
						line3 += " ";
					}
					if ((cellCounter + 1) == cellSize) {
						line3 += "#";
					}
				}
				cellCounter++;
			}
			System.out.println(line1);
			System.out.println(line2);
			if ((rowCounter + 1) == rowSize) {
				System.out.println(line3);
			}
			rowCounter++;
		}
	}

	
	public ArrayList<Row> getRows() {
		return rows;
	}

	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}
	
	/**
	 * Converts a Grid into string format.
	 */
	@Override
	public String toString() {
		String temp = "Grid [rows=[";
		int size = this.getRows().size();
		int counter = 0;
		for (Row item : this.getRows()) {
			temp += item.toString();
			counter++;
			if (counter < size) {
				temp += ", ";
			}
		}
		temp += "]]";
		return temp;
	}
}
