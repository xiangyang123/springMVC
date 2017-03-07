package com.zs.serviceImpl;

import com.zs.dao.UserDao;
import com.zs.pojo.User;
import com.zs.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zouxiang on 2017/3/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }
}
