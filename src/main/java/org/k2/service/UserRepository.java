package org.k2.service;

import org.k2.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String>{
    User save(User user);

    User findByName(String name);

    User findOne(String id);

    void delete(User user);
}
