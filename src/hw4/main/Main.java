package hw4.main;

import hw4.game.*;
import hw4.maze.classes.*;
import hw4.player.Player;
import java.util.Scanner;

/**
 * Main class for the Tears, Despair and Debugging game.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grid grid = new Grid();
        Game game = new Game(grid);

        Row startingRow = grid.getRows().get(grid.getRows().size() - 1);
        Cell startingCell = startingRow.getCells().get(startingRow.getCells().size() - 1);
        Player player = new Player(startingRow, startingCell);
        boolean escaped = false;

        while (!escaped) {
            grid.printGrid(player);

            System.out.print("\nMove (WASD): ");
            String input = scanner.nextLine().toUpperCase();

            Movement move = null;
            switch (input) {
                case "W":
                    move = Movement.UP;
                    break;
                case "A":
                    move = Movement.LEFT;
                    break;
                case "S":
                    move = Movement.DOWN;
                    break;
                case "D":
                    move = Movement.RIGHT;
                    break;
                default:
                    System.out.println("Invalid input, Use W, A, S, or D.");
                    continue;
            }
            boolean moved = game.play(move, player);
            if (!moved) {
                System.out.println("You can't move in that direction.");
            } else if (move == Movement.LEFT &&
                       player.getCurrentRow().getCells().indexOf(player.getCurrentCell()) == 0 &&
                       player.getCurrentCell().getLeft().toString().equals("EXIT")) {
                escaped = true;
                System.out.println("\nYou escaped the maze!");
            }
        }

        scanner.close();
    }
}
