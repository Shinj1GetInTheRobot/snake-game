package zen;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game {
    private final RandomEmptySquareGenerator resg;
    private IntegerProperty[][] board;
    private int snakeLength;
    private Square snakeHead;
    private Direction dir;

    public Game(int y, int x) { 
        snakeLength = Settings.getGrowth();
        board = createNewEmptyBoard(y, x);
        resg = new RandomEmptySquareGenerator(this);
        setHeadOnRandomSquare(snakeLength);
        setRandomSquare(-1);
    }
    public Game() {
        this(0, 0);
    }

    public void nextFrame() {
        try {
            snakeHead = getSquareAhead();
            if (board[snakeHead.y][snakeHead.x].get() == -1) {
                adjPositiveBoardValues(Settings.getGrowth());
                snakeLength += Settings.getGrowth();
                setRandomSquare(-1);
            }
            adjPositiveBoardValues(-1);
            board[snakeHead.y][snakeHead.x].set(snakeLength);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Game Over");
        }
    }

    public void setDirection(Direction direction) { 
        dir = direction; 
        nextFrame();
    }

    private void adjPositiveBoardValues(int value) {
        for (IntegerProperty[] row : board) {
            for (IntegerProperty entry : row) {
                if (entry.get() > 0) entry.set(entry.get() + value);
            }
        }
    }

    private Square getSquareAhead() {
        switch (dir) {
            case LEFT:
                return new Square(snakeHead.y, snakeHead.x - 1);
            case RIGHT:
                return new Square(snakeHead.y, snakeHead.x + 1);
            case UP:
                return new Square(snakeHead.y - 1, snakeHead.x);
            case DOWN:
                return new Square(snakeHead.y + 1, snakeHead.x);
        }
        return null; // Unsafe!
    }

    private void setHeadOnRandomSquare(int value) {
        snakeHead = resg.newRandomSquare();
        board[snakeHead.y][snakeHead.x].set(value);
    }
    private void setRandomSquare(int value) {
        Square rSquare = resg.newRandomSquare();
        board[rSquare.y][rSquare.x].set(value);
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
    private void printBoard(String title) {
        System.out.println(title);
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.printf("%2s ", board[y][x].get());
            }
            System.out.println();
        }
    }
}
