package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);

    List<UserInfo> findByNameIsContainingAllIgnoreCase(String userName);

}
