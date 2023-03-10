package com.freeedu.gatewayserver;


import com.alibaba.csp.sentinel.init.InitExecutor;
import com.freeedu.commonserver.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringBootApplication//(scanBasePackages = {"com.freeedu.commonserver"})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@Import({GlobalExceptionHandler.class})
public class GatewayServerMain {


    public static void main(String[] args) {
//        SpringApplication.run(GatewayServerMain.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(GatewayServerMain.class, args);
        System.setProperty("project.name",
                context.getEnvironment().getProperty("spring.application.name", "freeedu-order-server"));
        System.setProperty("csp.sentinel.dashboard.server",
                context.getEnvironment().getProperty("sentinel.dashboard.server", "localhost:8080"));
        InitExecutor.doInit();
    }


    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }


}
