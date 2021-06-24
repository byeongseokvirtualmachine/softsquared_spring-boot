package com.example.demo.src.servers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Servers {
    private int serverId;
    private int hostId;
    private int userId;
    private String serverType;
    private String serverName;
    private String profileImageUrl;
    private String inviteUrl;
    private String availableDM;
}