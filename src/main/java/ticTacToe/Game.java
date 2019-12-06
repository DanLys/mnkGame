package ticTacToe;

public class Game {
    private Player player1;
    private Player player2;

    public Game() {}

    public int play(Board board) {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(Board board, Player player, int num) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);

        if (result == Result.WIN) {
            return num;
        } else if (result == Result.LOSE) {
            return 3 - num;
        } else if (result == Result.DRAW) {
            return 0;
        } else {
            return -1;
        }
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
