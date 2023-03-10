package com.freeedu.userserver.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.freeedu.userserver.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insert(User user);


    User selectById(Long id);

    User selectBy2Id(Long id);


}
