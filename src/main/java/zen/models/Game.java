package zen.models;

import javafx.beans.property.ReadOnlyObjectProperty;

public interface Game {
    public void nextFrame();
    public void playIfReady();
    public Board getBoard();
    public Status getStatus();
    public Snake getSnake(int id);
    public ReadOnlyObjectProperty<Status> statusProperty();
}
