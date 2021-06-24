package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
//
@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private int userId;
    private String status;
    private String userName;
    private String userTag;
    private String flagOnOff;
    private String profileImageUrl;
    private String phone;
    private String email;
    private String password;
    private String nitro;

}
