package hw4.player;

import hw4.maze.classes.*;

/**
 * Player class that keeps track of the player's position.
 */
public class Player {

    private Row currentRow;
    private Cell currentCell;
    
    /**
     * Constructs a Player with the given starting row and cell.
     *
     * @param currentRow the Row where the player starts
     * @param currentCell the Cell within the row where the player starts
     */
    public Player(Row currentRow, Cell currentCell) {
        this.currentRow = currentRow;
        this.currentCell = currentCell;
    }

    public Row getCurrentRow() {
        return currentRow;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentRow(Row currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
    
    /**
	 * Converts a Player into string format.
	 */
    @Override
    public String toString() {
        return "Player [currentCell=" + currentCell + ", currentRow=" + currentRow + "]";
    }
}
