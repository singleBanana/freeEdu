package com.freeedu.userserver.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.freeedu.userserver.dao.UserDao;
import com.freeedu.userserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;


    public User findById(Long id) {
        return new User(1L, "王八蛋", 1);
    }



//    @DS("slave")
    public User findByUserId(Long id) {
        return userDao.selectById(id);
    }

    public User findBwyUserId(Long id) {
        return userDao.selectBy2Id(id);
    }

    public int save(User user) {
        return userDao.insert(user);
    }

}
