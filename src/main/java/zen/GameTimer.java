package zen;

import javafx.animation.AnimationTimer;

public class GameTimer {
    private final Game game;
    private final AnimationTimer timer;
    private boolean isRunning;

    public GameTimer(Game game, int speed) {
        this.game = game;
        this.isRunning = false;
        timer = new AnimationTimer() {
            private final long delay = speed * 1000000L;
            private long prevNow;
            @Override
            public void handle(long now) {
                if (now - prevNow > delay) {
                    game.nextFrame();
                    prevNow = now;
                }
            }
        };
    }

    public void start() { 
        isRunning = true;
        timer.start();
    }
    public void stop() {
        timer.stop(); 
        isRunning = false;
    }
    public boolean isRunning() {
        return isRunning;
    }
}
