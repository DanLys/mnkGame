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
        output.println("Enter tour count");
        int c = input.nextInt();
        output.println("Enter player count");
        int cntPeople = input.nextInt();

        Tourney tourney = new Tourney(cntPeople, row, column, k, c);
        tourney.play();
    }
}
