package org.k2.service;

import org.k2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class K2BoardService {

    @Autowired
    private BoardFactory boardFactory;

    @Autowired
    UserBoardStatusRepository userBoardStatusRepository;

    @Autowired
    UserScoreRecordRepository userScoreRecordRepository;

    public IK2ChessBoard createNewChessBoard(User user) {
        IK2ChessBoard board = boardFactory.createNewChessBoard();
        userBoardStatusRepository.save(new UserBoardStatus(user, board.getCurrentStatus()));
        userScoreRecordRepository.save(new UserScoreRecord(user, 0));
        return board;
    }
}


