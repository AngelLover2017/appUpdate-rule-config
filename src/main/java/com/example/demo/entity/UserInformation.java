package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserInformation {
    private Integer id;

    @NotNull(message = "username 不能为空")
    @NotBlank(message = "username 不能为空")
    private String username;

    @NotNull(message = "password 不能为空")
    @NotBlank(message = "password 不能为空")
    private String password;

    @NotNull(message = "permission 不能为空")
    private Integer permission;
}