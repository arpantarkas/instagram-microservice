package com.arpan.instagram.util;

import com.arpan.instagram.dto.LikeDto;
import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.UserDto;
import com.arpan.instagram.model.Like;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.model.User;

public class EntityMapper {

    public static User toUser(UserDto userDto) {
        return new User(userDto.getName(),
                        userDto.getUsername(),
                        userDto.getEmail()
                        );
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getName(),
                user.getEmail(),
                user.getUsername());
    }

    public static Post toPost(PostDto postDto, User user) {
        return new Post(postDto.getCaption(), user);

    }


}

