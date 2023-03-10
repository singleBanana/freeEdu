package com.freeedu.gatewayserver.feign.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {


    private Long id;
    private String name;
    private Integer sex;

    public UserDto(Long id, String name, Integer sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public UserDto() {
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
