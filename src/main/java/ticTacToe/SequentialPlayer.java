package ticTacToe;

public class SequentialPlayer implements Player {
    public SequentialPlayer() {}

    @Override
    public Move move(Position position, Cell cell) {
        for (int i = 0; i < position.getRows(); i++) {
            for (int j = 0; j < position.getColumns(); j++) {
                if (position.getCell(i, j) == Cell.E) {
                    return new Move(i, j, cell);
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
