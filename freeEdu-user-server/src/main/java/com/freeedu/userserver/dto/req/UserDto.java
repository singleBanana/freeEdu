package com.freeedu.userserver.dto.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "{user.name.notnull}")
    @Length(min = 1, max = 10, message = "name should be 1-10")
    private String name;

    @NotNull(message = "sex can not empty")
    @Range(min = 0, max = 1, message = "sex should be 0-1")
    private Integer sex;

    @NotNull(message = "{user.age.notnull}")
    @Max(value = 100, message = "Please set valid age")
    private Integer age;

    @Email(message = "invalid email")
    private String email;
}
