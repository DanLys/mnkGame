package ticTacToe;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        final PrintStream output = new PrintStream(System.out);

        output.println("Enter size square");
        int row = InputNext(input, output);
        int column = InputNext(input, output);
        output.println("Enter count to win");
        int k = InputNext(input, output);
        output.println("Enter tour count");
        int c = InputNext(input, output);
        output.println("Enter player count");
        int cntPeople = InputNext(input, output);

        Tourney tourney = new Tourney(cntPeople, row, column, k, c);
        tourney.play();
    }

    private static int InputNext(Scanner input, PrintStream output) {
        while (true) {
            if (input.hasNextInt()) {
                return input.nextInt();
            } else {
                input.next();
                output.print("Invalid enter");
            }
        }
    }
}
