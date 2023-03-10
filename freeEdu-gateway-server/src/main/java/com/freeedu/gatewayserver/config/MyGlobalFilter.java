package com.freeedu.gatewayserver.config;

import com.freeedu.commonserver.entity.Resp;
import com.freeedu.gatewayserver.feign.dto.UserDto;
import com.freeedu.gatewayserver.feign.impl.UserFeignServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author Wd
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    UserFeignServiceImpl userFeignService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入自定义的全局过滤器 MyGlobalFilter" + new Date());

        HttpHeaders headers = exchange.getRequest().getHeaders();
        String author = headers.getFirst("author");
        log.info("MyGlobalFilter request author = " + author);
        String user = headers.getFirst("user");
        Resp<UserDto> resp = userFeignService.findById(Long.valueOf(user));
        if (resp.getCode() == 0) {
            log.info("MyGlobalFilter request user = " + resp.getData().toString());
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
