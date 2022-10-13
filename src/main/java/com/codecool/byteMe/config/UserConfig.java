package com.codecool.byteMe.config;

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
                        new User("Zénó", 18, "zeno@byte.me"),
                        new User("Vanda", 18, "vanda@byte.me"),
                        new User("Erik", 18, "erik@byte.me"),
                        new User("Dani", 69420, "dani@byte.me")
                )
        );
    }
}
