package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<UserInfo> findByNameIsContainingAllIgnoreCase(String userName);
}
