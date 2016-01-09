package org.k2.controller;

import org.k2.model.User;
import org.k2.service.UserService;
import org.k2.validation.DirectionValidation;
import org.k2.validation.UserNameValidation;
import org.k2.viewmodel.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class K2Controller {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register/{who}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RegisterInfo> register(@PathVariable("who") @UserNameValidation String who) {
        try {
            User user = userService.persistUser(who);
            RegisterInfo registerInfo = new RegisterInfo(user.getName(), true, "");
            return new ResponseEntity<>(registerInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/move/{direction}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> move(@PathVariable("direction") @DirectionValidation String direction) {

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/score/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getScore(@PathVariable("user") @UserNameValidation String user) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/scores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getScores(@RequestParam("offset") int offset, @RequestParam("size") int size) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

