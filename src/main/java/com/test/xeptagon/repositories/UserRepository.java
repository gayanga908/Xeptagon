package com.test.xeptagon.repositories;

import com.test.xeptagon.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUserName(String userName);
}
