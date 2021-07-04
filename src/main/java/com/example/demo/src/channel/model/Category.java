package com.example.demo.src.channel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private int cpId;
    private String status;
    private String goryType;
    private Date alertMute;
}
