package ticTacToe;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream output;
    private final Scanner input;

    public HumanPlayer(final PrintStream output, final Scanner input) {
        this.input = input;
        this.output = output;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            output.println("Position");
            output.println(position);
            output.println(cell + "'s move");
            output.println("Enter row and column");
            final Move move = new Move(inputNext(), inputNext(), cell);
            if (position.isValid(move)) {
                return move;
            }
            output.println("Move " + move + " is invalid");
        }
    }

    private int inputNext() {
        while (!input.hasNextInt()) {
            input.next();
            output.print("Invalid enter");
        }
        return input.nextInt();
    }
}
