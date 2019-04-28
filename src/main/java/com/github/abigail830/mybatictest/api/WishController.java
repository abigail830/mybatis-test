package com.github.abigail830.mybatictest.api;

import com.github.abigail830.mybatictest.api.dto.WishDTO;
import com.github.abigail830.mybatictest.domain.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{id}/wishes")
public class WishController {

    private WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping
    public List<WishDTO> getAllWishesForUser(@PathVariable Integer userId) {
        return wishService.getAllWishesByUser(userId).stream()
                .map(WishDTO::fromWish)
                .collect(Collectors.toList());
    }

}
