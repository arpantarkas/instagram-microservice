package com.arpan.instagram.controller;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.User;
import com.arpan.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseDto<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @PostMapping("/api/users")
    public ResponseDto<User> createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/api/users/{id}")
    public ResponseDto<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/api/users/{id}")
    public ResponseDto<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userDto, id);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseDto<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
