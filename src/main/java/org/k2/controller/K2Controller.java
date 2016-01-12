package org.k2.controller;

import javafx.util.Pair;
import org.codehaus.jackson.map.annotate.JsonView;
import org.k2.exception.*;
import org.k2.model.IK2ChessBoard;
import org.k2.model.MoveDirection;
import org.k2.model.User;
import org.k2.model.UserScoreRecord;
import org.k2.service.K2BoardService;
import org.k2.service.UserScoreService;
import org.k2.service.UserService;
import org.k2.validation.DirectionValidation;
import org.k2.validation.UserNameValidation;
import org.k2.viewmodel.BoardInfo;
import org.k2.viewmodel.MoveInfo;
import org.k2.viewmodel.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class K2Controller {
    @Autowired
    private UserService userService;

    @Autowired
    private K2BoardService k2BoardService;

    @Autowired
    private UserScoreService userScoreService;

    @RequestMapping(value = "/register/{who}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public BoardInfo register(@PathVariable("who") @UserNameValidation String who) throws ConflictException {
        User user = null;
        try {
            user = userService.persistUser(who);
        } catch (Exception e) {
            throw new ConflictException(e.getMessage());
        }
        IK2ChessBoard board = k2BoardService.createNewChessBoard(user);
        return new BoardInfo(user.getName(), true, board.getCurrentStatus());
    }

    @RequestMapping(value = "/reset/{who}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public BoardInfo reset(@PathVariable("who") @UserNameValidation String who) throws NotFoundException {
        try {
            User user = userService.getUser(who);
            IK2ChessBoard board = k2BoardService.resetUserChessBoard(user);
            return new BoardInfo(user.getName(), true, board.getCurrentStatus());
        } catch (Exception e) {
            throw new NotFoundException("not found");
        }
    }

    @RequestMapping(value = "/move/{who}/{direction}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public MoveInfo move(@PathVariable("who") @UserNameValidation String who,
                                       @PathVariable("direction") @DirectionValidation String direction)
            throws Exception, GameOverException, InvalidMoveException {
        Pair<String, Integer> move = k2BoardService.move(who, MoveDirection.valueOf(direction));
        return new MoveInfo(who, true, move.getKey(), "move succeed.", move.getValue());
    }

    @RequestMapping(value = "/score/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo getScore(@PathVariable("user") @UserNameValidation String who) throws Exception {
        User user = userService.getUser(who);
        int userScore = userScoreService.getUserScore(user);
        return new UserInfo(who, userScore);
    }

    @RequestMapping(value = "/scores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> getScores(@RequestParam("offset") int offset, @RequestParam("size") int size) {
        List<UserScoreRecord> userScoreRecords = userScoreService.getUserScores(offset, size);

        return userScoreRecords.stream()
                .map(record -> new UserInfo(record.getUser().getName(), record.getScore()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleConflictException(ConflictException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MoveException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public MoveInfo handleMoveException(MoveException ex) {
        return new MoveInfo(ex.getName(), false, ex.getStatus(), ex.getMessage(), -1);
    }

    @ExceptionHandler(GameOverException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleGameOver(GameOverException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidMoveException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleInvalidMove(InvalidMoveException ex) {
        return ex.getMessage();
    }
}


