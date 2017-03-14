package com.zs.serviceImpl;

import com.zs.dao.UserDao;
import com.zs.pojo.User;
import com.zs.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zouxiang on 2017/3/2.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Cacheable("getUserById")
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @CacheEvict(value = {"getUserById","findList"},allEntries = true)
    public int saveUser(User user) {
        return userDao.insert(user);
    }

    @Cacheable("findList")
    public List<User> findList() {
        return userDao.findList();
    }

    @CacheEvict(value={"getUserById","findList"},allEntries = true)
    public void rename(int id, String name) {
        User user = userDao.selectByPrimaryKey(id);
        user.setUserName(name);
        userDao.updateByPrimaryKey(user);
    }
}
