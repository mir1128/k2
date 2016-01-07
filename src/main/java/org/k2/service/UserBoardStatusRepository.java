package org.k2.service;

import org.k2.model.User;
import org.k2.model.UserBoardStatus;
import org.springframework.data.repository.Repository;

public interface UserBoardStatusRepository extends Repository<UserBoardStatus, String> {
    UserBoardStatus findByUser(User user);

    UserBoardStatus save(UserBoardStatus userBoardStatus);
}
