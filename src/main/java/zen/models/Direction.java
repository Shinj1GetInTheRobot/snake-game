package zen.models;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE; 

    public final Direction getOpposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            default: return NONE;
        }
    }

    public boolean isParallelTo(Direction d) {
        return (this == d || this.getOpposite() == d);
    }

    public boolean isNotSet() {
        return (this == NONE);
    }
}
