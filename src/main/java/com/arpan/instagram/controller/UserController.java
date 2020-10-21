package com.arpan.instagram.controller;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.exception.BaseException;
import com.arpan.instagram.model.User;
import com.arpan.instagram.service.UserService;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseDto<UserDto> getAllUsers(Pageable pageable) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    userService.getAllUsers(pageable));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }

    }

    @PostMapping("/api/users")
    public ResponseDto<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "User Created",
                    Collections.singletonList(userService.createUser(userDto)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @GetMapping("/api/users/{id}")
    public ResponseDto<UserDto> getUser(@PathVariable Long id) {
        try {
            return ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(userService.getUser(id)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseDto<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "User updated",
                    Collections.singletonList(userService.updateUser(userDto, id)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseDto<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseUtil.setSuccessResponse(
                    200,
                    "User Deleted",
                    Collections.emptyList());
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }


}
