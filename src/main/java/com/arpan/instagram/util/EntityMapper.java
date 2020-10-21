package com.arpan.instagram.util;

import com.arpan.instagram.dto.LikeDto;
import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.Like;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.model.User;

public class EntityMapper {

    public static User toUser(UserDto userDto) {
        return new User()
                .setName(userDto.getName())
                .setEmail(userDto.getEmail())
                .setUsername(userDto.getUsername());
    }

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }

    public static Post toPost(PostDto postDto, User user) {
        return new Post(postDto.getCaption(), user);

    }


}

