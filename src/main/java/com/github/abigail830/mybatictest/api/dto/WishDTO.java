package com.github.abigail830.mybatictest.api.dto;

import com.github.abigail830.mybatictest.domain.model.Wish;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishDTO {

    int id;
    Timestamp createTime;
    String description;

    public static WishDTO fromWish(Wish wish) {
        return new WishDTO(wish.getId(), wish.getCreateTime(), wish.getDescription());
    }

    public Wish toWish() {
        return new Wish(this.description);
    }

}
