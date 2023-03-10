package com.freeedu.userserver;

import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.config.TransportConfig;
import com.freeedu.commonserver.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.freeedu.userserver", "com.freeedu.commonserver"})
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@Import({GlobalExceptionHandler.class})
@EnableSwagger2
public class UserCenterApplicationMain {


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(UserCenterApplicationMain.class, args);
        System.setProperty(TransportConfig.HEARTBEAT_CLIENT_IP, "localhost");
        System.setProperty("project.name",
                context.getEnvironment().getProperty("spring.application.name", "freeedu-user-server"));
        System.setProperty("csp.sentinel.dashboard.server",
                context.getEnvironment().getProperty("sentinel.dashboard.server", "localhost:8080"));
        InitExecutor.doInit();

    }

//    @PostConstruct
//    public void initFlowRule() {
//        List<FlowRule> ruleList = new ArrayList<>();
//        FlowRule rule = new FlowRule();
//        rule.setRefResource("Hello");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule.setCount(2);
//        ruleList.add(rule);
//        FlowRuleManager.loadRules(ruleList);
//    }
}
