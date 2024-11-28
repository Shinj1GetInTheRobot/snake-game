package zen.models;

public class Square {
    public final int x;
    public final int y;

    public Square(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public boolean overlaps(Square square) {
        return (square.x == this.x && square.y == this.y);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", y, x);
    }
}
