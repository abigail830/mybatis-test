package com.github.abigail830.mybatictest.api;

import com.github.abigail830.mybatictest.api.dto.SimpleUserResponseDTO;
import com.github.abigail830.mybatictest.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/userlist")
    public String wishlist(Model model) {

        final List<SimpleUserResponseDTO> responseList = userService.getAllUsers().stream()
                .map(SimpleUserResponseDTO::fromUser).collect(Collectors.toList());

        model.addAttribute("simpleUserList", responseList);
        return "userlist";
    }
}
