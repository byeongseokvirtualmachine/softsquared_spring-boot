package com.example.demo.src.channel.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Channel {
    private int chId;
    private String chName;
    private String chSubject;
    private String chType;
    private String status;
    private Date alertMute;
}
