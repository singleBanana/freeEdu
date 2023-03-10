package com.freeedu.gatewayserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(value = "FREEEDU-ORDER-SERVER")
public interface OrderFeignService {




}
