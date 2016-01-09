package org.k2.model;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class K2ChessBoard implements IK2ChessBoard {
    private final int WIDTH = 4;
    private int[][] board = new int[WIDTH][WIDTH];

    private IGenerateNumberStrategy numberGenerator;

    public K2ChessBoard() {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int row = random.nextInt(WIDTH - 1);
            int column = random.nextInt(WIDTH - 1);

            board[row][column] = random.nextBoolean() ? 2 : 4;
        }
    }

    public K2ChessBoard(String boardString) {
        List<String> numbers = Arrays.asList(boardString.split(","));
        for (int row = 0; row < WIDTH; ++row) {
            for (int column = 0; column < WIDTH; ++column) {
                board[row][column] = Integer.parseInt(numbers.get(row * WIDTH + column));
            }
        }
    }

    @Override
    public Pair<String, Integer> move(MoveDirection moveDirection) {
        return null;
    }

    @Override
    public IK2ChessBoard setGenerateNumberStrategy(IGenerateNumberStrategy numberGenerator) {
        this.numberGenerator = numberGenerator;
        return this;
    }
}

