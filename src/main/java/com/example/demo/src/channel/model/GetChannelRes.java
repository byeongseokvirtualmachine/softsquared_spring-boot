package com.example.demo.src.channel.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetChannelRes {
    private int chId;
    private String chName;
    private String chSubject;
    private String chType;
    private String status;
    private Date alertMute;
}
