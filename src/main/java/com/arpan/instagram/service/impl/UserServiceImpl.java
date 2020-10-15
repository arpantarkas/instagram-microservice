package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.User;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.service.UserService;
import com.arpan.instagram.util.EntityMapper;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDto<User> getAllUsers(Pageable pageable) {
        return ResponseUtil.setSuccessResponse(200, "Success", userRepository.findAll(pageable).getContent());
    }

    @Override
    public ResponseDto<User> getUser(Long id) {
        return userRepository.findById(id).map(user -> ResponseUtil.setSuccessResponse(
                200,
                "Success",
                Collections.singletonList(user)
        )).orElseGet(() -> ResponseUtil.setErrorResponse(404, "User Not Found"));
    }

    @Override
    public ResponseDto<User> createUser(UserDto userDto) {
        try {
            List<User> userList = Collections.singletonList(userRepository.save(EntityMapper.toUser(userDto)));
            return ResponseUtil.setSuccessResponse(201, "User Successfully Created", userList);
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.setErrorResponse(400, "Duplicate Username found, can't create new user. Change username and try again!");
        }
    }

    @Override
    public ResponseDto<?> updateUser(UserDto userDto, Long id) {
        return userRepository.findById(id).map(userUpdated -> {
            userUpdated.setName(userDto.getName());
            userUpdated.setUsername(userDto.getUsername());
            userUpdated.setEmail(userDto.getEmail());
            try {
                return ResponseUtil.setSuccessResponse(
                        200,
                        "User updated",
                        Collections.singletonList(userRepository.save(userUpdated)));
            } catch (DataIntegrityViolationException e) {
                return ResponseUtil.setErrorResponse(500, "Username Already taken");
            }
        }).orElseGet(() ->  ResponseUtil.setErrorResponse(404, "User doesn't exist"));
    }

    @Override
    public ResponseDto<Object> deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
                userRepository.delete(user);
                return ResponseUtil.setSuccessResponse(
                        200,
                        "User Deletion successful",
                        Collections.emptyList());
        }).orElseGet(() -> ResponseUtil.setErrorResponse(404, "User not found"));
    }


}
