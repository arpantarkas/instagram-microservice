package com.arpan.instagram.service;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    ResponseDto<Post> getPostsByUserId(Long userId, Pageable pageable);
    ResponseDto<Post> getPostByIdAndUserId(Long userId, Long postId);
    ResponseDto<?> createPost(Long userId, PostDto postDto);
    ResponseDto<Post> updatePost(Long userId, Long postId, String caption);
    ResponseDto<Object> deletePost(Long userId, Long postId);
}
