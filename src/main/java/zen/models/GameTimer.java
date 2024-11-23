package zen.models;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    private final Game game;
    private final long delay;
    private long prevNow;
    private boolean isRunning;

    public GameTimer(Game game, int speed) {
        this.game = game;
        delay = speed * 1000000L; // Convert Nanoseconds to Milliseconds
    }

    @Override
    public void handle(long now) {
        if (now - prevNow > delay) {
            game.nextFrame();
            prevNow = now;
        }
    }

    @Override
    public void start() { 
        isRunning = true;
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        isRunning = false;
    }
    public boolean isRunning() {
        return isRunning;
    }

    
}
