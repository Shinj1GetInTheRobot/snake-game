package zen.models;

public interface Game {
    public void nextFrame();
    public void play();

    public int getBoardHeight();
    public int getBoardWidth();
    public Status getStatus();

    public boolean currentDirectionIs(Direction direction);
    public boolean futureDirectionIs(Direction direction);
    public boolean futureFutureDirectionIs(Direction direction);
    public void setCurrentDirection(Direction direction);
    public void setFutureDirection(Direction direction);
    public void setFutureFutureDirection(Direction direction);


}
