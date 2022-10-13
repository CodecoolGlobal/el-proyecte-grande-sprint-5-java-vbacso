package com.codecool.byteMe;

import com.codecool.byteMe.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserConfig {
    @Bean
    public Set<User> getBaseDaoUsers() {
        return new HashSet<>(
                Set.of(
                        new User("Zénó", 18),
                        new User("Vanda", 18),
                        new User("Erik", 18),
                        new User("Dani", 69420)
                )
        );
    }
}
