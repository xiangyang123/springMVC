package com.zs.service;

import com.zs.pojo.User;

import java.util.List;

/**
 * Created by zouxiang on 2017/3/2.
 */

public interface UserService {
    User getUserById(int userId);

    int saveUser(User user);

    List<User> findList();

    void rename(int id, String zouxiang);
}
