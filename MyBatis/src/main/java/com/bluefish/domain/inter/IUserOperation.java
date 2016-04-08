package com.bluefish.domain.inter;

import com.bluefish.domain.User;

import java.util.List;
import java.util.Set;

/**
 * Created by elaine on 2016/4/3.
 */
public interface IUserOperation {

    Set<User> getAllUser();

    User getUserById(int id2);

    void deleteUser(User name);

    void updateUser(User user);

    void insertUser(User user);

}
