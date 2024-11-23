package zen.models;

import java.util.List;
import java.util.Random;

public class RandomEmptySquareGenerator {
    private final Random r;
    private final Game game;

    public RandomEmptySquareGenerator(Game game) {
        this.r = new Random();
        this.game = game;
    }
    public RandomEmptySquareGenerator(long seed, Game game) {
        this.r = new Random(seed);
        this.game = game;
    }

    public Square newRandomSquare() {
        List<Square> emptySquares = game.getEmptySquares();
        return emptySquares.get(r.nextInt(emptySquares.size()));
    }
}
