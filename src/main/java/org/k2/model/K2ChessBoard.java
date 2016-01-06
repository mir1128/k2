package org.k2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class K2ChessBoard {
    private static final double NUM_RATE_FOR_2 = 80;
    private static final double NUM_RATE_FOR_4 = 20;
    private static final int NUM_FOR_4 = 4;
    private static final int NUM_FOR_2 = 2;
    private Random random = new Random();

    private int[][] board = new int[4][4];

    public K2ChessBoard() {
        init();
    }

    private void init() {
        for (int i = 0; i < 2; i++) {
            int x = random.nextInt(board.length - 1);
            int y = random.nextInt(board.length - 1);
            writeToBoard(new Point(x, y));
        }
    }

    private List<Point> allNullLocation() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }

    private void addNewNumber() {
        List<Point> points = allNullLocation();
        writeToBoard(points.get(random.nextInt(points.size()-1)));
    }

    private void writeToBoard(Point point) {
        board[point.getX()][point.getY()] = getRandomValue();
    }

    private int getRandomValue() {
        int rate = random.nextInt(100);
        return rate < NUM_RATE_FOR_2 ? NUM_FOR_2 : NUM_FOR_4;
    }

    public String toString() {
        String result = "";
        for (int[] rows : board) {
            for (int column : rows) {
                result += column + ",";
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
