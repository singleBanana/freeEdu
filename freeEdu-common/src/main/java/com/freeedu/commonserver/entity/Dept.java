package com.freeedu.commonserver.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dept implements Serializable {

    private Integer deptNo;
    private String deptName;
    private String dbSource;
}
