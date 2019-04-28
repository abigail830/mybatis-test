package com.github.abigail830.mybatictest.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Wish {

    int id;
    Timestamp createTime;
    String description;

    public Wish(int id, Timestamp createTime, String description) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
    }

    public Wish(String description) {
        this.description = description;
    }
}
