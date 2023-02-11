package com.takisjoeapp.laundryaja.feature.user.domain.usecase;

import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.feature.user.helper.UserBuilder;

public class UserUsecaseImpl implements UserUsecase{

    @Override
    public User getUser() {
        User user = new UserBuilder().setAdmin(true).build();
        return user;
    }
}
