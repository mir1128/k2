package org.k2.model;

import javafx.util.Pair;

public interface IK2ChessBoard {
    Pair<String, Integer> move(MoveDirection moveDirection);

    IK2ChessBoard setGenerateNumberStrategy(IGenerateNumberStrategy generateNumberStrategy);
}
