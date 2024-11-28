package zen.models;

import javafx.beans.property.ReadOnlyBooleanProperty;

public interface Game {
    public void nextFrame();
    public void play();

    public ReadOnlyBooleanProperty gameOverProperty();
    public boolean isGameOver();
    public int getBoardHeight();
    public int getBoardWidth();
    public boolean isPlaying();

    public boolean currentDirectionIs(Direction direction);
    public boolean futureDirectionIs(Direction direction);
    public boolean futureFutureDirectionIs(Direction direction);
    public void setCurrentDirection(Direction direction);
    public void setFutureDirection(Direction direction);
    public void setFutureFutureDirection(Direction direction);


}
