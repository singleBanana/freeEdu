package com.freeedu.gatewayserver.feign;

import com.freeedu.commonserver.entity.Resp;
import com.freeedu.gatewayserver.feign.dto.UserDto;
import com.freeedu.gatewayserver.feign.hystrix.UserFeignServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "FREEEDU-USER-SERVER",
        fallback = UserFeignServiceHystrix.class,
        path = "/user/v1/feign")
public interface UserFeignService {


    /**
     * @return
     * @Description 通过id获取用户信息
     * @Param id
     **/
    @RequestMapping(value = "/user/findById", method = RequestMethod.GET)
    public Resp<UserDto> findById(@RequestParam Long id);

}
