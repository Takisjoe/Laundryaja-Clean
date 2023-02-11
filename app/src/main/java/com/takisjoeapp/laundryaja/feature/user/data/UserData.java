package com.takisjoeapp.laundryaja.feature.user.data;

import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;

public interface UserData {

    User getData();

    String getId();

    String getLaundryId();

    String getBranchId();
}
