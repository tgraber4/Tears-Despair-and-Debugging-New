package hw4.game;

import hw4.maze.classes.*;
import hw4.player.Player;

/**
 * Game class to manage grid logic and player movement.
 */
public class Game {

    private Grid grid;
    
    /**
     * Constructs a Game with a specified grid.
     *
     * @param grid The Grid object to use
     */
    public Game(Grid grid) {
        this.grid = grid;
    }

    /**
     * Constructs a Game with a randomly generated grid of a given size if it
     * is in between 3 and 7.
     *
     * @param size the size of the grid
     */
    public Game(int size) {
        if (size < 3 || size > 7) {
            this.grid = null;
        } else {
            this.grid = new Grid(size);
        }
    }

    /**
     * Attempts to move the player in the specified direction, if allowed.
     *
     * @param movement the direction in which the player wants to move
     * @param player the Player object to be moved
     * @return true if player moves or escapes the grid
     */
    public boolean play(Movement movement, Player player) {
        if (movement == null || player == null) return false;

        int currentRowIndex = grid.getRows().indexOf(player.getCurrentRow());
        int currentColIndex = player.getCurrentRow().getCells().indexOf(player.getCurrentCell());

        switch (movement) {
            case UP:
                if (player.getCurrentCell().getUp() == CellComponents.APERTURE && currentRowIndex > 0) {
                    player.setCurrentRow(grid.getRows().get(currentRowIndex - 1));
                    player.setCurrentCell(grid.getRows().get(currentRowIndex - 1).getCells().get(currentColIndex));
                    return true;
                }
                break;
            case DOWN:
                if (player.getCurrentCell().getDown() == CellComponents.APERTURE && currentRowIndex < grid.getRows().size() - 1) {
                    player.setCurrentRow(grid.getRows().get(currentRowIndex + 1));
                    player.setCurrentCell(grid.getRows().get(currentRowIndex + 1).getCells().get(currentColIndex));
                    return true;
                }
                break;
            case LEFT:
                if (player.getCurrentCell().getLeft() == CellComponents.APERTURE && currentColIndex > 0) {
                    player.setCurrentCell(player.getCurrentRow().getCells().get(currentColIndex - 1));
                    return true;
                } else if (player.getCurrentCell().getLeft() == CellComponents.EXIT && currentColIndex == 0) {
                    return true;
                }
                break;
            case RIGHT:
                if (player.getCurrentCell().getRight() == CellComponents.APERTURE && currentColIndex < player.getCurrentRow().getCells().size() - 1) {
                    player.setCurrentCell(player.getCurrentRow().getCells().get(currentColIndex + 1));
                    return true;
                }
                break;
        }

        return false;
    }
    

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Creates a grid of a given size.
     *
     * @param size the size of the grid (must be between 3 and 7)
     * @return a new Grid object
     */
    public Grid createRandomGrid(int size) {
        if (size < 3 || size > 7) return null;
        return new Grid(size);
    }
    
    /**
	 * Converts a Game into string format.
	 */
    @Override
    public String toString() {
        return "Game [grid=" + grid + "]";
    }
} 
