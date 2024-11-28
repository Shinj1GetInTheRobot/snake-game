package zen.models;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE; 

    public static final Direction getOpposite(Direction d) {
        switch (d) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            default: return NONE;
        }
    }
}
