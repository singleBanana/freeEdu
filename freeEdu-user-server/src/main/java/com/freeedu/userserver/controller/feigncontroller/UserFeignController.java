package com.freeedu.userserver.controller.feigncontroller;

import com.freeedu.commonserver.entity.Resp;
import com.freeedu.userserver.domain.User;
import com.freeedu.userserver.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/feign/user")
public class UserFeignController {

    @Autowired
    UserService userService;


    //服务端熔断
    //一旦该方法失败并抛出了异常信息后，会自动调用  @HystrixCommand 注解标注的 fallbackMethod 指定的方法
    @HystrixCommand(fallbackMethod = "dept_TimeoutHandler",
            commandProperties =
                    //规定 5 秒钟以内就不报错，正常运行，超过 5 秒就报错，调用指定的方法
                    {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @GetMapping("/findById")
    public Resp<User> findById(Long id) {

        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return Resp.success(userService.findById(id));
    }


    // 当服务出现故障后，调用该方法给出友好提示
    public Resp<User> dept_TimeoutHandler(Long id) {
        return Resp.error("FreeEdu中文网提醒您，系统繁忙请稍后再试！" + "线程池：" + Thread.currentThread().getName());
    }
}
