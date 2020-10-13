package com.arpan.instagram.service;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto);
//    List<Post> getALlPosts();
}
