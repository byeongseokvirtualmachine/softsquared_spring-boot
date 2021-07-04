package com.example.demo.src.mate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostMateRes {
    private int mateListId;
    private int userId;
    private int mateId;
    private String status;
}
