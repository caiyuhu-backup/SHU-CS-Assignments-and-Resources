package com.shu.cloudordering.model.dto;

import lombok.Data;

@Data
public class LoginFormDTO {
    private String phone;
    private String code;
    private String password;
    private Integer isVendor;
}