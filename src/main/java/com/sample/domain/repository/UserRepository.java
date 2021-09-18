package com.sample.domain.repository;

import com.sample.domain.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findByUsername(String userName);
}
