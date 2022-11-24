package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.UserModel;

/**
 * A Projection for the {@link UserModel} entity
 */
public interface UserInfo {
    Long getId();

    String getName();
}