package com.example.demo.src.servers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetServerRes {

    private int serverId;
    private int hostId;
    private int userId;
    private String serverType;
    private String serverName;
    private String profileImageUrl;
    private String inviteUrl;
    private String availableDM;
}
