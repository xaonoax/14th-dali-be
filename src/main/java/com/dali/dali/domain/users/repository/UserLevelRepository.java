package com.dali.dali.domain.users.repository;

import com.dali.dali.domain.users.entity.User;

public interface UserLevelRepository {
    void updateLevel(User user);
}
