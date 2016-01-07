package org.k2.service;

import org.k2.model.User;
import org.k2.model.UserScoreRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserScoreRecordRepository extends Repository<UserScoreRecord, String> {
    UserScoreRecord findByUser(User user);

    UserScoreRecord save(UserScoreRecord userScoreRecord);

    @Query(value = "select * from user_score_record order by score desc " +
            " limit :offset , :size", nativeQuery = true)
    List<UserScoreRecord> findByOffsetAndSize(@Param("offset") int offset, @Param("size") int size);
}
