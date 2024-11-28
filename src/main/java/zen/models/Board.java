package zen.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Board {

    public static final int APPLE = -3;
    public static final int DEAD = -2;
    public static final int OUTOFBOUNDS = -1;
    public static final int EMPTYSQUARE = 0;

    private final Random rand;
    private IntegerProperty[][] board;
    // Warning: board includes the visible grid + a hidden outer layer of invisible squares
    //          used for when a snake goes out of bounds
    // Magic Numbers:
    //      0 = Empty Square
    //      1 = Snake1
    //      2 = Snake2
    //      3 = Snake3, etc...
    //     -1 = Hidden Outer Square
    //     -2 = Apple

    public Board(int y, int x) {
        this.board = createNewBoard(y, x);
        this.rand = new Random();
    }

    public int getValue(int y, int x) { 
        return board[y][x].get(); 
    }
    public int getValue(Square square) {
        return board[square.y][square.x].get();
    }

    public void setSquare(Square square, int value) {
        board[square.y][square.x].set(value);
    }
    public void resetSquare(Square square) {
        board[square.y][square.x].set(EMPTYSQUARE);
    }
    
    public void setRandApple() {
        Square square = newRandomEmptySquare();
        board[square.y][square.x].set(APPLE);
    }
    public void setRandApples(int quantity) {
        for (int i = 0; i < quantity; i++) setRandApple();
    }

    public ReadOnlyIntegerProperty squareProperty(int y, int x) {
        return board[y][x];
    }
    public ReadOnlyIntegerProperty squareProperty(Square square) {
        return board[square.y][square.x];
    }

    public int getBoardHeight() { return board.length; }
    public int getBoardWidth() { return board[0].length; }

    // public void adjPositiveBoardValues(int value) {
    //     for (IntegerProperty[] row : board) {
    //         for (IntegerProperty entry : row) {
    //             if (entry.get() > 0) entry.set(entry.get() + value);
    //         }
    //     }
    // }

    public Square newRandomEmptySquare() {
        List<Square> emptySquares = getEmptySquares();
        return emptySquares.get(rand.nextInt(emptySquares.size()));
    }

    private List<Square> getEmptySquares() {
        List<Square> emptySquares = new ArrayList<Square>();
        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board[0].length - 1; x++) {
                if (board[y][x].get() == EMPTYSQUARE) emptySquares.add(new Square(y, x));
            }
        }
        return emptySquares;
    }

    private IntegerProperty[][] createNewBoard(int y, int x) {
        IntegerProperty[][] newBoard = new IntegerProperty[y][x];
        // Set main grid squares to 0
        for (int i = 1; i < y-1; i++) {
            for (int j = 1; j < x-1; j++) {
                newBoard[i][j] = new SimpleIntegerProperty(EMPTYSQUARE);
            }
        }
        // Set outer layer squares to -1
        for (int i = 0; i < x; i++) newBoard[0][i] = new SimpleIntegerProperty(OUTOFBOUNDS);
        for (int i = 0; i < x; i++) newBoard[y-1][i] = new SimpleIntegerProperty(OUTOFBOUNDS);
        for (int j = 0; j < y; j++) newBoard[j][0] = new SimpleIntegerProperty(OUTOFBOUNDS);
        for (int j = 0; j < y; j++) newBoard[j][x-1] = new SimpleIntegerProperty(OUTOFBOUNDS);
        return newBoard;
    }

    @Override
    public String toString() {
        String b = "";
        for (IntegerProperty[] row : board) {
            for (IntegerProperty entry : row) {
                b += String.format("%2d ", entry.get());
            }
            b += "\n";
        }
        return b;
    }
}
