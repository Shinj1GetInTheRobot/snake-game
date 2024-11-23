package zen.models;

public class Square {
    private int x;
    private int y;

    public Square(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int x() { return x; }
    public int y() { return y; }
    
    public void incrX() { x++; }
    public void decrX() { x--; }
    public void incrY() { y++; }
    public void decrY() { y--; }

    @Override
    public String toString() {
        return String.format("[%d, %d]", y, x);
    }
}
