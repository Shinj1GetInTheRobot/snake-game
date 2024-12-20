package zen.models;

public class Settings {
    private static int speed = 100;
    private static int growth = 1;
    private static int multiplayers = 2;
    private final static int gridWidth = 50;
    private final static int gridHeight = 25;

    public static void setSpeed(int newSpeed) { speed = newSpeed; } 
    public static void setGrowth(int newGrowth) { growth = newGrowth; }
    public static void setNumOfMultiPlayers(int newNum) { multiplayers = newNum; }

    public static int getSpeed() { return speed; }
    public static int getGrowth() { return growth; }
    public static int getNumOfMultiPlayers() { return multiplayers; }
    public static int getGridWidth() { return gridWidth; }
    public static int getGridHeight() { return gridHeight; }
}
