package zen.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Snake {
    private int length;
    private Square head;
    private Direction currentDir;
    private Direction futureDir;
    private IntegerProperty score;

    public Snake(int length, Square startingSquare) {
        this.length = length;
        this.head = startingSquare;
        this.score = new SimpleIntegerProperty(0);
    }

    // Unsafe! Snake can move off the board or crash into object!
    public void move() {
        currentDir = futureDir;
        switch (currentDir) {
            case LEFT:
                head.decrX(); break;
            case RIGHT:
                head.incrX(); break;
            case UP:
                head.decrY(); break;
            case DOWN:
                head.incrY(); break;
        }
    }

    public final int getLength() { return length; }
    public final Square getHead() { return head; }
    public final Direction getCurrentDirection() { return currentDir; }
    public final int getScore() { return score.get(); }
    public final ReadOnlyIntegerProperty scoreProperty() { return score; }
    
    public void incrLength(int incr) {
        length += incr;
    }
    public void setFutureDirection(Direction futureDir) {
        this.futureDir = futureDir;
    }
    public void incrScore() {
        score.set(score.get() + 1);
    }

}
