package org.k2.model;

import org.k2.model.generator.ProbabilityGenerator;
import org.springframework.stereotype.Component;

@Component
public class BoardFactory {
    public static IK2ChessBoard createNewChessBoard() {
        return new K2ChessBoard().setGenerateNumberStrategy(new ProbabilityGenerator());
    }

    public static IK2ChessBoard createFromString(String boardStatus) {
        return new K2ChessBoard(boardStatus).setGenerateNumberStrategy(new ProbabilityGenerator());
    }
}


