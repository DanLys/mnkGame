package ticTacToe;

import java.util.Arrays;

public class ServerBoard implements Board, Position {
    private final int rows;
    private final int columns;
    private final int k;
    private int emptyCells;
    private Cell[][] cells;
    private Cell turn;

    public ServerBoard(int rows, int columns, int k) {
        this.rows = rows;
        this.columns = columns;
        this.k = k;
        this.cells = new Cell[rows][columns];

        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        emptyCells = rows * columns;

        turn = Cell.X;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    private int checkCnt(int v1, int v2, Move move) {
        int cnt = 0;
        int i = move.getRow();
        int j = move.getColumn();
        while (i >= 0 && i < rows && j >= 0 && j < columns && cells[i][j] == move.getCell()) {
            i += v1;
            j += v2;
            cnt++;
        }

        return cnt;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        if (turn == Cell.X) {
            turn = Cell.O;
        } else {
            turn = Cell.X;
        }

        cells[move.getRow()][move.getColumn()] = move.getCell();
        emptyCells--;

        if (won(move, 0, 1) || won(move, 1, 0) || won(move, 1, 1) || won(move, 1, -1)) {
            return Result.WIN;
        }
        for (int i = -1; i <= 0; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i < 0 || j < 0) {
                    if (checkCnt(i, j, move) + checkCnt(-i, -j, move) - 1 >= k) {
                        return Result.WIN;
                    }
                }
            }
        }
        if (emptyCells == 0) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    private boolean won(Move move, int i, int j) {
        return checkCnt(i, j, move) + checkCnt(-i, -j, move) - 1 >= k;
    }

    @Override
    public boolean isValid(Move move) {
        return move.getRow() >= 0 && move.getRow() < rows
                && move.getColumn() >= 0 && move.getColumn() < columns
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getCell();
    }

    @Override
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder table = new StringBuilder(" ");
        for (int i = 0; i < columns; i++) {
            table.append(i);
            table.append(' ');
        }
        table.append('\n');

        for (int i = 0; i < rows; i++) {
            table.append(i);
            for (int j = 0; j < columns; j++) {
                table.append(cells[i][j]);
                table.append(' ');
            }
            table.append('\n');
        }
        return table.toString();
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }
}
