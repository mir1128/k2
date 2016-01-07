package org.k2.service;

import javassist.NotFoundException;
import org.k2.model.User;
import org.k2.model.UserScoreRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserScoreService {
    @Autowired
    UserScoreRecordRepository userScoreRecordRepository;

    Map<String, Integer> getUserScores(int offset, int size) {
        return null;
    }

    int getUserScore(User user) throws NotFoundException {
        UserScoreRecord userScoreRecord = userScoreRecordRepository.findByUser(user);
        if (userScoreRecord != null) {
            return userScoreRecord.getScore();
        }
        throw new NotFoundException("not found");
    }
}


