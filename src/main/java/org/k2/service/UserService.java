package org.k2.service;

import org.k2.exception.NotFoundException;
import org.k2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User persistUser(String who) throws Exception {
        if (userRepository.findByName(who) != null) {
            throw new Exception("already exist!");
        }
        return userRepository.save(new User(who, new Date()));
    }

    public User getUser(String who) throws Exception {
        User user = userRepository.findByName(who);
        if (user == null) {
            throw new NotFoundException("user not exist!");
        }
        return user;
    }
}

