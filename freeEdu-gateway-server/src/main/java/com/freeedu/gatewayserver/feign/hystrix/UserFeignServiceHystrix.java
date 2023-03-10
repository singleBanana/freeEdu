package com.freeedu.gatewayserver.feign.hystrix;

import com.freeedu.commonserver.entity.Resp;
import com.freeedu.gatewayserver.feign.UserFeignService;
import com.freeedu.gatewayserver.feign.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class UserFeignServiceHystrix implements UserFeignService {

    @Override
    public Resp<UserDto> findById(@RequestParam Long id) {
        return Resp.error("调用userFeign.findById 失败");
    }

}
