package com.github.abigail830.mybatictest.api;

import com.github.abigail830.mybatictest.api.dto.WishDTO;
import com.github.abigail830.mybatictest.domain.WishService;
import com.github.abigail830.mybatictest.domain.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishes")
public class WishController {

    private WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/{wishId}")
    public WishDTO getWishById(@PathVariable Integer wishId) {
        final Wish wishById = wishService.getWishById(wishId);
        return WishDTO.fromWish(wishById);
    }

    @PutMapping("/{wishId}")
    public WishDTO updateWishById(@PathVariable Integer wishId) {
        final Wish wishById = wishService.getWishById(wishId);
        return WishDTO.fromWish(wishById);
    }

    @DeleteMapping("/{wishId}")
    public WishDTO deleteWishById(@PathVariable Integer wishId) {
        final Wish wishById = wishService.getWishById(wishId);
        return WishDTO.fromWish(wishById);
    }

}
