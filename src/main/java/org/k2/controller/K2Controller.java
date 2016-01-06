package org.k2;

import org.k2.model.UserBoardStatus;
import org.k2.service.UserBoardStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class K2Controller {
    @Autowired
    UserBoardStatusRepository userBoardStatusRepository;

    @RequestMapping(value = "/register/{who}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> register(@PathVariable("who") String who) {


        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

