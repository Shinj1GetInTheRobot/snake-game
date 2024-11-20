package zen;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private IntegerProperty[][] board;

    public Game(int y, int x) {
        board = createNewBoard(y, x);
    }
    public Game() {
        this(0, 0);
    }

    public int getEntry(int y, int x) { 
        return board[y][x].get();
    }

    public ReadOnlyIntegerProperty squareProperty(int y, int x) {
        return board[y][x];
    }

    public int getBoardHeight() { return board.length; }
    public int getBoardWidth() { return board[0].length; }

    public IntegerProperty[][] createNewBoard(int y, int x) {
        IntegerProperty[][] b = new SimpleIntegerProperty[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                b[i][j] = new SimpleIntegerProperty(0);
            }
        }
        return b;
    }

    // Testing
    private void printBoard() {
        System.out.println("Board:");
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(board[y][x].get() + " ");
            }
            System.out.println();
        }
    }
}
