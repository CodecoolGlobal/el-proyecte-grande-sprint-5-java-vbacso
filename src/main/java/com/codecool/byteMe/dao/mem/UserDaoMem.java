package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component("userDaoMem")
public class UserDaoMem implements UserDao {

    private static Set<User> data = new HashSet<>();

    @Override
    public User add(User user) {
        data.add(user);
        return user;
    }

    @Override
    public User find(String email) {
        throw new UnsupportedOperationException("Not implemented method: (find) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (find) for class: (UserDaoMem)
    }

    @Override
    public Set<User> getAllUser() {
        return data;

    }
}
