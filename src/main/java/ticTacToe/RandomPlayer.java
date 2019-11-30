package ticTacToe;

import java.util.Random;

public class RandomPlayer implements Player {
    final Random random;

    public RandomPlayer(Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(Position position, Cell cell) {
        while (true) {
            int row = random.nextInt();
            int column = random.nextInt();

            final Move move = new Move(row, column, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
