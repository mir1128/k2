package org.k2;

import org.k2.service.UserBoardStatusRepository;
import org.k2.validation.DirectionValidation;
import org.k2.validation.UserNameValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class K2Controller {
    @Autowired
    UserBoardStatusRepository userBoardStatusRepository;

    @RequestMapping(value = "/register/{who}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> register(@PathVariable("who") @UserNameValidation String who) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/move/{direction}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> move(@PathVariable("direction") @DirectionValidation String direction) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

