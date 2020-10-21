package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.exception.InvalidRequestException;
import com.arpan.instagram.exception.ResourceConflictException;
import com.arpan.instagram.exception.UserNotFoundException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent()
                .stream().map(EntityMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) {
        return userRepository.findById(id)
                .map(EntityMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            return EntityMapper.toUserDto(userRepository.save(EntityMapper.toUser(userDto)));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidRequestException("username already taken");
        }
    }

    @Override
    public User updateUser(UserDto userDto, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDto.getName());
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            try {
                return userRepository.save(user);
            } catch (DataIntegrityViolationException e) {
                throw new InvalidRequestException("username already taken");
            }
        }).orElseThrow(() ->  new UserNotFoundException(id.toString()));
    }

    @Override
    public void deleteUser(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            userRepository.delete(user.get());
        else
            throw new UserNotFoundException(id.toString());
    }


}
