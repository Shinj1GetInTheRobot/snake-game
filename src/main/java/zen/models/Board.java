package zen.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Board {
    private final Random rand;
    private IntegerProperty[][] board;

    public Board(int y, int x) {
        this.board = createNewBoard(y, x);
        this.rand = new Random();
    }

    public int getValue(int y, int x) { 
        return board[y][x].get();
    }
    public int getValue(Square square) {
        return board[square.y()][square.x()].get();
    }

    public void setSnakeHead(Snake snake) {
        board[snake.getHead().y()][snake.getHead().x()].set(snake.getLength());
    }
    
    public void setRandApple() {
        Square square = newRandomEmptySquare();
        board[square.y()][square.x()].set(-1);
    }
    public void setRandApples(int quantity) {
        for (int i = 0; i < quantity; i++) setRandApple();
    }

    public ReadOnlyIntegerProperty squareProperty(int y, int x) {
        return board[y][x];
    }
    public ReadOnlyIntegerProperty squareProperty(Square square) {
        return board[square.y()][square.x()];
    }

    public int getBoardHeight() { return board.length; }
    public int getBoardWidth() { return board[0].length; }

    public void adjPositiveBoardValues(int value) {
        for (IntegerProperty[] row : board) {
            for (IntegerProperty entry : row) {
                if (entry.get() > 0) entry.set(entry.get() + value);
            }
        }
    }

    public Square newRandomEmptySquare() {
        List<Square> emptySquares = getEmptySquares();
        return emptySquares.get(rand.nextInt(emptySquares.size()));
    }

    private List<Square> getEmptySquares() {
        List<Square> emptySquares = new ArrayList<Square>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x].get() == 0) emptySquares.add(new Square(y, x));
            }
        }
        return emptySquares;
    }

    private IntegerProperty[][] createNewBoard(int y, int x) {
        IntegerProperty[][] newBoard = new IntegerProperty[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                newBoard[i][j] = new SimpleIntegerProperty(0);
            }
        }
        return newBoard;
    }
}
