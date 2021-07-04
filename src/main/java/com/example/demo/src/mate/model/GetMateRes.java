package com.example.demo.src.mate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMateRes {
    public int MateListId;
    public int userId;
    public int mateId;
    public String status;
}
