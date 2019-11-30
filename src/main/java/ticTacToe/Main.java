package ticTacToe;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        final PrintStream output = new PrintStream(System.out);

        output.println("Enter size square");
        int row = input.nextInt();
        int column = input.nextInt();
        output.println("Enter count to win");
        int k = input.nextInt();

        final Game game = new Game(new HumanPlayer(), new HumanPlayer());
        int result;
        do {
            result = game.play(new TicTacToe(row, column, k));
            output.println("Game result " + result);
        } while (result != 0);
    }
}
