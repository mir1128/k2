package org.k2.model;

import javafx.util.Pair;
import org.k2.exception.GameOverException;
import org.k2.exception.InvalidMoveException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public Pair<String, Integer> move(MoveDirection moveDirection) throws GameOverException, InvalidMoveException {
        String prevStatus = getCurrentStatus();

        int score = 0;
        switch (moveDirection) {
            case Up:
                score = moveUp();
                break;
            case Down:
                score = moveDown();
                break;
            case Left:
                score = moveLeft();
                break;
            case Right:
                score = moveRight();
                break;
            default:
                break;
        }

        if (prevStatus.equals(getCurrentStatus())) {
            throw new InvalidMoveException("invalid move");
        }

        int nextPosition = findNextPosition();
        board[nextPosition] = numberGenerator.getNextNumber();

        if (isGameOver()) {
            throw new GameOverException("Game Over");
        }

        return new Pair<>(getCurrentStatus(), score);
    }

    private int moveRight() {
        int total = 0;
        for (int row = 0; row < WIDTH; ++row) {
            int[] result = compressOneLine(new int[]{board[row * WIDTH + 3], board[row * WIDTH + 2],
                    board[row * WIDTH + 1], board[row * WIDTH]});
            board[row * WIDTH + 3] = result[0];
            board[row * WIDTH + 2] = result[1];
            board[row * WIDTH + 1] = result[2];
            board[row * WIDTH] = result[3];
            total += result[4];
        }
        return total;
    }

    private int moveLeft() {
        int total = 0;
        for (int row = 0; row < WIDTH; ++row) {
            int[] result = compressOneLine(new int[]{board[row * WIDTH], board[row * WIDTH + 1],
                    board[row * WIDTH + 2], board[row * WIDTH + 3]});
            board[row * WIDTH] = result[0];
            board[row * WIDTH + 1] = result[1];
            board[row * WIDTH + 2] = result[2];
            board[row * WIDTH + 3] = result[3];
            total += result[4];
        }
        return total;
    }

    private int moveDown() {
        int total = 0;
        for (int column = 0; column < WIDTH; ++column) {
            int[] result = compressOneLine(new int[]{board[3 * WIDTH + column], board[2 * WIDTH + column],
                    board[WIDTH + column], board[column]});
            board[3 * WIDTH + column] = result[0];
            board[2 * WIDTH + column] = result[1];
            board[WIDTH + column] = result[2];
            board[column] = result[3];
            total += result[4];
        }

        return total;
    }

    private int moveUp() {
        int total = 0;
        for (int column = 0; column < WIDTH; ++column) {
            int[] result = compressOneLine(new int[]{board[column], board[WIDTH + column],
                    board[2 * WIDTH + column], board[3 * WIDTH + column]});
            board[column] = result[0];
            board[WIDTH + column] = result[1];
            board[2 * WIDTH + column] = result[2];
            board[3 * WIDTH + column] = result[3];
            total += result[4];
        }
        return total;
    }

    private int[] compressOneLine(int[] line) {
        int[] notZero = Arrays.stream(line).filter(n -> n != 0).toArray();
        int[] result = Arrays.stream(new int[WIDTH + 1]).map(n -> n = 0).toArray();

        switch (notZero.length) {
            case 0:
                break;
            case 1:
                result[0] = notZero[0];
                break;
            case 2:
                if (notZero[0] == notZero[1]) {
                    result[0] = notZero[0] * 2;
                    result[4] = notZero[0] * 2;
                } else {
                    result[0] = notZero[0];
                    result[1] = notZero[1];
                }
                break;
            case 3:
                if (notZero[0] == notZero[1]) {
                    result[0] = notZero[0] * 2;
                    result[1] = notZero[2];
                    result[4] = notZero[0] * 2;
                } else if (notZero[1] == notZero[2]) {
                    result[0] = notZero[0];
                    result[1] = notZero[1] * 2;
                    result[4] = notZero[1] * 2;
                } else {
                    result[0] = notZero[0];
                    result[1] = notZero[1];
                    result[2] = notZero[2];
                }
                break;
            case 4:
                if (notZero[0] == notZero[1]) {
                    if (notZero[2] == notZero[3]) {
                        result[0] = notZero[0] * 2;
                        result[1] = notZero[2] * 2;
                        result[4] = notZero[0] * 2 + notZero[2] * 2;
                    } else {
                        result[0] = notZero[0] * 2;
                        result[1] = notZero[2];
                        result[2] = notZero[3];
                        result[4] = notZero[0] * 2;
                    }
                } else {
                    result[0] = notZero[0];
                    if (notZero[1] == notZero[2]) {
                        result[1] = notZero[1] * 2;
                        result[2] = notZero[3];
                        result[4] = notZero[1] * 2;
                    } else if (notZero[2] == notZero[3]) {
                        result[1] = notZero[1];
                        result[2] = notZero[2] * 2;
                        result[4] = notZero[2] * 2;
                    } else {
                        result[1] = notZero[1];
                        result[2] = notZero[2];
                        result[3] = notZero[3];
                    }
                }
                break;
        }
        return result;
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

    private int findNextPosition() {
        List<Integer> nullIndex = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            if (board[i] == 0) {
                nullIndex.add(i);
            }
        }

        if (nullIndex.size() == 0) {
            return -1;
        }

        Random random = new Random();
        int n = random.nextInt(nullIndex.size());
        return nullIndex.get(n);
    }

    private boolean isGameOver() {
        int noneZeroCount = Arrays.stream(board).filter(n -> n != 0).sum();
        if (noneZeroCount != WIDTH * WIDTH) {
            return false;
        }

        for (int row = 0; row < WIDTH; ++row) {
            for (int column = 0; column < WIDTH; ++column) {
                try {
                    if ((board[row * WIDTH + column] != 0)
                            && (board[row * WIDTH + column] == board[row * WIDTH + column + 1]
                            || board[row * WIDTH + column] == board[row * WIDTH + column - 1]
                            || board[row * WIDTH + column] == board[(row - 1) * WIDTH + column]
                            || board[row * WIDTH + column] == board[(row + 1) * WIDTH + column])) {
                        return false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return true;
    }
}

