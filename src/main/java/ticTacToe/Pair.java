package ticTacToe;

public class Pair {
    private int num;
    private int data;

    public Pair(int data, int num) {
        this.data = data;
        this.num = num;
    }

    public int getData() {
        return data;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return (num + 1) + " " + data;
    }
}
