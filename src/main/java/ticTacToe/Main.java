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
        Player[] players = new Player[cntPeople];
        output.println("Enter how will play:");
        output.println("1 - Human Player");
        output.println("2 - Random Player");
        output.println("3 - Sequential Player");

        int ind = 0;
        while (ind < cntPeople) {
            int x = InputNext(input, output);
            switch (x) {
                case 1:
                    players[ind] = new HumanPlayer();
                    break;
                case 2:
                    players[ind] = new RandomPlayer();
                    break;
                case 3:
                    players[ind] = new SequentialPlayer();
                    break;
                default:
                    output.println("Invalid enter");
                    continue;
            }
            ind++;
        }


        Tourney tourney = new Tourney(cntPeople, row, column, k, c, players);
        tourney.play();
        System.out.println(tourney.getPlayerScore(1));
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
