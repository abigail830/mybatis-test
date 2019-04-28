package com.github.abigail830.mybatictest.domain.model;

import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
public class Wish {

    int id;
    Timestamp createTime;
    String description;

    public Wish(int id, Timestamp createTime, String description) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
    }
}
