package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("User not found in the DB");
            throw new UsernameNotFoundException("User not found in the DB");
        } else {
            System.out.printf("User found in the db %s", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        System.out.println(user.getEmail() + user.getPassword());
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}
