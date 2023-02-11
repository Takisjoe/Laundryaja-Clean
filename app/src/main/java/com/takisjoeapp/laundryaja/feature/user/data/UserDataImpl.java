package com.takisjoeapp.laundryaja.feature.user.data;

import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.feature.user.helper.UserBuilder;

public class UserDataImpl implements UserData{


    @Override
    public User getData() {
        User user = new UserBuilder().build();
        return user;    }

    @Override
    public String getId() {
        User user = new UserBuilder().build();
        return user.getId();
    }

    @Override
    public String getLaundryId() {
        User user = new UserBuilder().build();
        return user.getIdLaundry();
    }

    @Override
    public String getBranchId() {
        User user = new UserBuilder().build();
        return user.getIdBranch();
    }
}
