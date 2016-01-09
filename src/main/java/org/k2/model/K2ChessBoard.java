package org.k2.model;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Random;

public class K2ChessBoard implements IK2ChessBoard {
    private final int WIDTH = 4;
    private int[] board = new int[WIDTH * WIDTH];

    private IGenerateNumberStrategy numberGenerator;

    public K2ChessBoard() {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int row = random.nextInt(WIDTH - 1);
            int column = random.nextInt(WIDTH - 1);

            board[row * WIDTH + column] = random.nextBoolean() ? 2 : 4;
        }
    }

    public K2ChessBoard(String boardString) {
        board = Arrays.stream(boardString.substring(1, boardString.length() - 1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
    }

    @Override
    public Pair<String, Integer> move(MoveDirection moveDirection) {
        return null;
    }

    @Override
    public String getCurrentStatus() {
        return Arrays.toString(board).replace(" ", "");
    }

    @Override
    public IK2ChessBoard setGenerateNumberStrategy(IGenerateNumberStrategy numberGenerator) {
        this.numberGenerator = numberGenerator;
        return this;
    }
}

