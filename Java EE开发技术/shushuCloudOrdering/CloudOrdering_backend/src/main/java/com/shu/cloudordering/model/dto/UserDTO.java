package com.shu.cloudordering.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String nickname;
    private String userImg;
    private String realname;
    private String userMobile;
    private Integer vendorId;
}
