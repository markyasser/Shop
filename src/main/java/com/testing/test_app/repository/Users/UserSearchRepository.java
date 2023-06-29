package com.testing.test_app.repository.Users;

import com.testing.test_app.model.User;

public interface UserSearchRepository {
    User findByName(String name);
}
