package com.arpan.instagram.service;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<Post> getPostsByUserId(Long userId, Pageable pageable);
    Post getPostByIdAndUserId(Long userId, Long postId);
    Post createPost(Long userId, PostDto postDto);
    Post updatePost(Long userId, Long postId, String caption);
    void deletePost(Long userId, Long postId);
}
