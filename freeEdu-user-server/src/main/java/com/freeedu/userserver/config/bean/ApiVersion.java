package com.freeedu.userserver.config.bean;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @Description //自定义版本标签
 * @Param
 * @return
 * @return null
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    String value();
}
