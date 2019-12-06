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
            int row = random.nextInt(position.getRows());
            int column = random.nextInt(position.getColumns());

            final Move move = new Move(row, column, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
