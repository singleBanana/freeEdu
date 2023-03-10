package com.freeedu.ordersystemserver;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableConfigurationProperties(AutoServiceRegistrationProperties.class)
public class OrderSystemMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OrderSystemMain.class, args);
        System.setProperty("project.name",
                context.getEnvironment().getProperty("spring.application.name", "freeedu-order-server"));
        System.setProperty("csp.sentinel.dashboard.server",
                context.getEnvironment().getProperty("sentinel.dashboard.server", "localhost:8080"));
        InitExecutor.doInit();
    }

}
