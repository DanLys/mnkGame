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
    private Pair[] playerPoints;

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

    public Tourney(int cnt, int rows, int columns, int k, int c, Player[] players) {
        this.c = c;
        this.cnt = cnt;
        this.rows = rows;
        this.columns = columns;
        this.k = k;
        output = new PrintStream(System.out);

        this.players = players;

        currentTable = new int[cnt][cnt];
        totalTable = new int[cnt][cnt];
        playerPoints = new Pair[cnt];
    }

    public void play() {
        Game game = new Game();
        for (int i = 0; i < c; i++) {
            playTour(game);
            printTable(currentTable);
            clear();
        }
        printTable(totalTable);
        printWinner();
    }

    private void replaceTables(int i, int j, int val) {
        currentTable[i][j] = val;
        totalTable[i][j] += val;
    }

    private void playTour(Game game) {
        for (int i = 0; i < cnt; i++) {
            for (int j = i + 1; j < cnt; j++) {
                game.setPlayers(players[i], players[j]);
                int result = game.play(new PlayerBoard(rows, columns, k));
                if (result == 0) {
                    replaceTables(i, j, 1);
                    replaceTables(j, i, 1);
                } else if (result == 1) {
                    replaceTables(i, j, 3);
                } else {
                    replaceTables(j, i, 3);
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
        output.println();
    }

    private void clear() {
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                currentTable[i][j] = 0;
            }
        }
    }

    private void printWinner() {
        output.println("Total result");
        playerPoints = getPlayersScore();
        for (int i = 0; i < cnt; i++) {
            output.println(playerPoints[i] + " ");
        }
        output.println();
    }

    public Pair[] getPlayersScore() {
        for (int i = 0; i < cnt; i++) {
            int point = 0;
            for (int j = 0; j < cnt; j++) {
                point += totalTable[i][j];
            }
            playerPoints[i] = new Pair(point, i);
        }

        Arrays.sort(playerPoints, new Tourney.cmp());
        return playerPoints;
    }

    public int getPlayerScore(int ind) {
        int result = 0;
        for (int i = 0; i < cnt; i++) {
            result += totalTable[ind][i];
        }
        return result;
    }
}
