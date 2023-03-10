package com.freeedu.gatewayserver.feign.impl;

import com.freeedu.commonserver.entity.Resp;
import com.freeedu.gatewayserver.feign.UserFeignService;
import com.freeedu.gatewayserver.feign.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeignServiceImpl implements UserFeignService {

    @Autowired
    UserFeignService userFeignService;


    @Override
    public Resp<UserDto> findById(Long id) {
        return userFeignService.findById(id);
    }
}
