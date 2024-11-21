package zen;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private IntegerProperty[][] board;
    private final RandomEmptySquareGenerator r;

    public Game(int y, int x) { 
        board = createNewEmptyBoard(y, x);
        r = new RandomEmptySquareGenerator(this);
        Square rSquare = r.getRandomSquare();
        board[rSquare.y][rSquare.x].set(1);
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

    public IntegerProperty[][] createNewEmptyBoard(int y, int x) {
        IntegerProperty[][] b = new SimpleIntegerProperty[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                b[i][j] = new SimpleIntegerProperty(0);
            }
        }
        return b;
    }

    public List<Square> getEmptySquares() {
        List<Square> emptySquares = new ArrayList<Square>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x].get() == 0) emptySquares.add(new Square(y, x));
            }
        }
        return emptySquares;
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
