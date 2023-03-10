package com.freeedu.userserver.domain;

import lombok.Data;

@Data
public class User {


    private Long id;
    private String name;
    private Integer sex;

    public User(Long id, String name, Integer sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
