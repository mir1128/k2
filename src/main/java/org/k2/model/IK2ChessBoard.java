package org.k2.model;

import javafx.util.Pair;
import org.k2.exception.GameOverException;
import org.k2.exception.InvalidMoveException;
import org.k2.exception.MoveException;

public interface IK2ChessBoard {
    Pair<String, Integer> move(MoveDirection moveDirection) throws MoveException, GameOverException, InvalidMoveException;

    String getCurrentStatus();

    IK2ChessBoard setGenerateNumberStrategy(IGenerateNumberStrategy generateNumberStrategy);
}
