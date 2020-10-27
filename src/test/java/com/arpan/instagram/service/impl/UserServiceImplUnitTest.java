package com.arpan.instagram.service.impl;

import com.arpan.instagram.InstaUnitTestUtils;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.User;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.util.EntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class UserServiceImplUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto sampleUserDto;

    @BeforeEach
    void setUp() {
        var sampleUserDto = InstaUnitTestUtils.sampleUserDto();
        var user = EntityMapper.toUser(sampleUserDto);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetAllUsersSuccessfully() {
        var users = InstaUnitTestUtils.userList();
        doReturn(users)
                .when(userRepository)
                .findAll();

        List<User> userList = userRepository.findAll();
        verify(userRepository, times(1)).findAll();
        assertEquals(userList, users);
    }

    @Test
    void shouldGetUserByUserId() {
        doReturn(user)
                .when(userRepository)
                .findById(1L);

        var user = userService.getUser(1L);
        verify(userService, times(1)).getUser(1L);
        assertEquals(sampleUserDto, user);
    }

    @Test
    void shouldCreateUserSuccessfully() {

        doReturn(user)
                .when(userRepository)
                .save(user);

        var userActual = userRepository.save(EntityMapper.toUser(sampleUserDto));
        verify(userRepository, times(1)).save(user);
        assertEquals(user, userActual);
    }

    @Test
    void updateUser() {

//        var user = userService.updateUser()
    }

    @Test
    void deleteUser() {
    }
}