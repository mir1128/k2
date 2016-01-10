package org.k2.controller;

import org.codehaus.jackson.map.annotate.JsonView;
import org.k2.exception.ConflictException;
import org.k2.exception.NotFoundException;
import org.k2.model.IK2ChessBoard;
import org.k2.model.User;
import org.k2.service.K2BoardService;
import org.k2.service.UserService;
import org.k2.validation.DirectionValidation;
import org.k2.validation.UserNameValidation;
import org.k2.viewmodel.BoardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class K2Controller {
    @Autowired
    private UserService userService;

    @Autowired
    private K2BoardService k2BoardService;

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

    @RequestMapping(value = "/move/{direction}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> move(@PathVariable("direction") @DirectionValidation String direction) {

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/score/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getScore(@PathVariable("user") @UserNameValidation String user) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/scores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getScores(@RequestParam("offset") int offset, @RequestParam("size") int size) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public String handleConflictException(ConflictException ex) {
        return ex.getMessage();
    }
}


