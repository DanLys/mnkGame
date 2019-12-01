package ticTacToe;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

public class Tourney {
    private final int cnt;
    private final Player[] players;
    private final int rows;
    private final int columns;
    private final int k;
    private final int c;
    private int[][] currentTable;
    private int[][] totalTable;
    private final PrintStream output;

    static class cmp implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            if (a.getData() < b.getData()) {
                return 1;
            } else if (a.getData() == b.getData()) {
                return a.getNum() < b.getNum() ? 1 : -1;
            }
            return -1;
        }
    }

    public Tourney(int cnt, int rows, int columns, int k, int c) {
        this.c = c;
        this.cnt = cnt;
        this.rows = rows;
        this.columns = columns;
        this.k = k;
        output = new PrintStream(System.out);

        players = new Player[cnt];
        for (int i = 0; i < cnt; i++) {
            players[i] = new HumanPlayer();
        }

        currentTable = new int[cnt][cnt];
        totalTable = new int[cnt][cnt];
    }

    public void play() {
        for (int i = 0; i < c; i++) {
            playTour();
            printTable(currentTable);
            clear();
        }
        printTable(totalTable);
        printWinner();
    }

    private void playTour() {
        for (int i = 0; i < cnt; i++) {
            for (int j = i + 1; j < cnt; j++) {
                Game game = new Game(players[i], players[j]);
                int result = game.play(new TicTacToe(rows, columns, k));
                if (result == 0) {
                    currentTable[i][j] = 1;
                    currentTable[j][i] = 1;
                    totalTable[i][j] += 1;
                    totalTable[j][i] += 1;
                } else if (result == 1) {
                    currentTable[i][j] = 3;
                    totalTable[i][j] += 3;
                } else {
                    currentTable[j][i] = 3;
                    totalTable[j][i] += 3;
                }
            }
        }
    }

    private void printTable(int[][] table) {
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                output.print(table[i][j] + " ");
            }
            output.println();
        }
    }

    private void clear() {
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                currentTable[i][j] = 0;
            }
        }
    }

    private void printWinner() {
        Pair[] playerPoints = new Pair[cnt];
        for (int i = 0; i < cnt; i++) {
            int point = 0;
            for (int j = 0; j < cnt; j++) {
                point += totalTable[i][j];
            }
            playerPoints[i] = new Pair(point, i);
        }

        Arrays.sort(playerPoints, new Tourney.cmp());
        for (int i = 0; i < cnt; i++) {
            output.println(playerPoints[i] + " ");
        }
    }
}
