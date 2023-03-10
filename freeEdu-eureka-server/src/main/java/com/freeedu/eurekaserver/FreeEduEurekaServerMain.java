package com.freeedu.eurekaserver;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableEurekaServer
public class FreeEduEurekaServerMain {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(FreeEduEurekaServerMain.class);

        SpringApplication.run(FreeEduEurekaServerMain.class, args);
    }


    @PreDestroy
    public void destroy(){
        DiscoveryManager.getInstance().shutdownComponent();
    }
}
