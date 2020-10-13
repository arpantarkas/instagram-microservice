package com.arpan.instagram.service;

import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.User;
import org.springframework.data.domain.Pageable;

public interface UserService {

    ResponseDto<User> getAllUsers(Pageable pageable);
    ResponseDto<User> getUser(Long id);
    ResponseDto<User> createUser(UserDto userDto);
    ResponseDto<?> updateUser(UserDto userDto, Long id);
    ResponseDto<Object> deleteUser(Long id);

}
