package com.github.abigail830.mybatictest.api;

import com.github.abigail830.mybatictest.api.dto.SimpleUserResponseDTO;
import com.github.abigail830.mybatictest.api.dto.UserRequestDTO;
import com.github.abigail830.mybatictest.service.User;
import com.github.abigail830.mybatictest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<SimpleUserResponseDTO> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(SimpleUserResponseDTO::fromUser).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SimpleUserResponseDTO getUserById(@PathVariable Integer id){
        final User user = userService.getUserById(id);
        return SimpleUserResponseDTO.fromUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addUser(@RequestBody UserRequestDTO userRequestDTO){
        final User user = userRequestDTO.toUser();
        userService.addUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable Integer id,
                           @RequestBody UserRequestDTO userRequestDTO){
        final User user = userRequestDTO.toUser(id);
        userService.updateUser(user);
    }
}
