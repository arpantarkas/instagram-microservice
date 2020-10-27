package com.arpan.instagram;

import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstaUnitTestUtils {

    public static User sampleUser() {
        return new User()
                .setId(1L)
                .setName("Arpan Tarkas")
                .setUsername("arpantarkas")
                .setEmail("arpantarkas@gmail.com");
    }
    public static UserDto sampleUserDto() {
        return new UserDto()
                .setName("Arpan Tarkas")
                .setUsername("arpantarkas")
                .setEmail("arpantarkas@gmail.com");
    }

    public static List<User> userList() {
        List<User> users = new ArrayList<>();
        users.add(new User()
                .setName("Arpan Tarkas")
                .setUsername("arpantarkas")
                .setEmail("arpantarkas@gmail.com"));
        users.add(new User()
                .setName("Tarkas")
                .setUsername("arpantarkas2")
                .setEmail("arpantarkas@gmail.com"));
        return users;
    }
}
