package com.codecool.byteMe.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationUserModel {

    private String name;
    private int age;
    private String email;
    private String password;

}
