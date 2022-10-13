package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.codecool.byteMe.model.User.toSingleton;

@Component("userDao")
public class UserDaoMem implements UserDao {

    private static Set<User> data;

    private UserDaoMem(Set<User> getBaseDaoUsers) {
        data = new HashSet<>();
        data.addAll(getBaseDaoUsers);
    }

    @Override
    public User add(User user) {
        data.add(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return data.stream().filter(user -> user.getEmail().equals(email)).collect(toSingleton());
    }

    @Override
    public Set<Post> findByUser(UUID userId){
        return data.stream().filter(user -> user.getId()
                        .equals(userId)).collect(toSingleton()).getPosts().stream().collect(Collectors.toSet());

    }

    @Override
    public Set<User> getAllUser() {
        return data;
    }
}
