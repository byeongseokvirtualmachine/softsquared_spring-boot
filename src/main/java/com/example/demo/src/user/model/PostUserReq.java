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
    private String userName;
    private String email;
    private String password;
}
