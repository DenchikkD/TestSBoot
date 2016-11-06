package com.company.repository;

import com.company.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Onlin on 29.10.2016.
 */
@Repository
public interface DAOUser extends CrudRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
}
