package org.k2.service;

import javafx.util.Pair;
import org.k2.exception.GameOverException;
import org.k2.exception.InvalidMoveException;
import org.k2.exception.NotFoundException;
import org.k2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;

@Component
public class K2BoardService {

    @Autowired
    private BoardFactory boardFactory;

    @Autowired
    UserBoardStatusRepository userBoardStatusRepository;

    @Autowired
    UserScoreRecordRepository userScoreRecordRepository;

    @Autowired
    UserService userService;

    public IK2ChessBoard createNewChessBoard(User user) {
        IK2ChessBoard board = boardFactory.createNewChessBoard();
        userBoardStatusRepository.save(new UserBoardStatus(user, board.getCurrentStatus()));
        userScoreRecordRepository.save(new UserScoreRecord(user, 0));
        return board;
    }

    public IK2ChessBoard resetUserChessBoard(User user) {
        IK2ChessBoard board = boardFactory.createNewChessBoard();

        UserBoardStatus userBoardStatus = userBoardStatusRepository.findByUser(user);
        userBoardStatus.setStatus(board.getCurrentStatus());
        userBoardStatusRepository.save(userBoardStatus);

        UserScoreRecord userScoreRecord = userScoreRecordRepository.findByUser(user);
        userScoreRecord.setScore(0);
        userScoreRecordRepository.save(userScoreRecord);

        return board;
    }

    public Pair<String, Integer> move(String who, MoveDirection direction) throws Exception, GameOverException, InvalidMoveException {
        User user = userService.getUser(who);
        UserBoardStatus userBoardStatus = userBoardStatusRepository.findByUser(user);
        if (userBoardStatus == null) {
            throw new NotFoundException("not found user board");
        }

        IK2ChessBoard k2ChessBoard = boardFactory.createFromString(userBoardStatus.getStatus());

        Pair<String, Integer> result = k2ChessBoard.move(direction);

        UserScoreRecord userScoreRecord = userScoreRecordRepository.findByUser(user);
        if (userScoreRecord == null) {
            throw new NotFoundException("not found user board");
        }
        userBoardStatus.setStatus(result.getKey());
        userBoardStatusRepository.save(userBoardStatus);

        userScoreRecord.setScore(userScoreRecord.getScore() + result.getValue());
        userScoreRecord.updateMaxScore(result.getValue());
        userScoreRecordRepository.save(userScoreRecord);

        return new Pair<>(result.getKey(), userScoreRecord.getScore());
    }
}



