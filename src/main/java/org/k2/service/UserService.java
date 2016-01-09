package org.k2.service;

import org.k2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void persistUser(String who) throws Exception {
        if (userRepository.findByName(who) != null) {
            throw new Exception("already exist!");
        }
        userRepository.save(new User(who, new Date()));
    }
}

