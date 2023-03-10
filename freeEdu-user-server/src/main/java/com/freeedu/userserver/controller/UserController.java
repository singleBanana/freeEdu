package com.freeedu.userserver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.freeedu.commonserver.config.Signature;
import com.freeedu.commonserver.entity.Resp;
import com.freeedu.userserver.config.bean.ApiVersion;
import com.freeedu.userserver.domain.User;
import com.freeedu.userserver.dto.req.UserDto;
import com.freeedu.userserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Api
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

//    @Signature
    @ApiOperation("findById")
    @GetMapping("/findById")
    public Resp<User> findById(Long id) {

        return Resp.success(userService.findByUserId(id));
    }

    @SentinelResource(value = "findById2")
    @GetMapping("/findById2")
    public Resp<User> findById2(Long id) {
        return Resp.success(userService.findBwyUserId(id));
    }

    @PostMapping("/saveUser")
    public Resp<Integer> saveUser(@Valid @RequestBody UserDto userDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                log.error("Invalid Parameter : object - {},field - {},errorMessage - {}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return Resp.error("invalid parameter");
        }

        User user = new User(null, userDto.getName(), userDto.getSex());
        return Resp.success(userService.save(user));
    }

    @GetMapping("/i18n")
    public Resp<String> i18n() {
        Locale locale = LocaleContextHolder.getLocale();
        String language = messageSource.getMessage("welcome.txt", null, locale);
        return Resp.success(language);
    }

    @GetMapping("/{v}/get")
    public Resp getUser(@PathVariable("v") String v) {
        return Resp.success(1);
    }


    @ApiVersion("1.0.0")
    @GetMapping("/{v}/get")
    public Resp getUserV1() {
        return Resp.success(2);
    }

    @ApiVersion("1.1.0")
    @GetMapping("/{v}/get")
    public Resp getUserV11() {
        return Resp.success(3);
    }

    @ApiVersion("1.1.2")
    @GetMapping("/{v}/get")
    public Resp getUserV112() {
        return Resp.success(4);
    }
}
