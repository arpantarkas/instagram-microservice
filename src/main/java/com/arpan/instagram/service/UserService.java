package com.arpan.instagram.service;

import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(Pageable pageable);
    UserDto getUser(Long id);
    UserDto createUser(UserDto userDto);
    User updateUser(UserDto userDto, Long id);
    void deleteUser(Long id);

}
