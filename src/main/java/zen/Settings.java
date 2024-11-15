package zen;

public class Settings {
    private static int speed = 100;
    private static int growth = 1;

    public static void setSpeed(int newSpeed) {
        speed = newSpeed;
    } 
    public static void setGrowth(int newGrowth) {
        growth = newGrowth;
    }

    public static int getSpeed() {
        return speed;
    }
    public static int getGrowth() {
        return growth;
    }
}
