package org.k2.service;

import org.k2.model.User;
import org.k2.model.UserBoardStatus;
import org.springframework.data.repository.Repository;

import javax.validation.constraints.NotNull;

public interface UserBoardStatusRepository extends Repository<UserBoardStatus, String> {
    @NotNull
    UserBoardStatus findByUser(User user);

    UserBoardStatus save(@NotNull UserBoardStatus userBoardStatus);
}
