package com.arpan.instagram.controller;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.service.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(@Qualifier("postServiceImpl") PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/users/{user_id}/posts")
    public ResponseDto<Post> getPostsByUserId(@PathVariable(value = "user_id") Long userId,
                                              Pageable pageable) {
        return postService.getPostsByUserId(userId, pageable);
    }


    @GetMapping("/api/users/{user_id}/posts/{post_id}")
    public ResponseDto<Post> getPostById(@PathVariable(value = "user_id") Long userId,
                                         @PathVariable(value = "post_id") Long postId) {
        return postService.getPostById(userId, postId);
    }


    @PostMapping("/api/users/{user_id}/posts")
    public ResponseDto<?> createPost(@PathVariable(value = "user_id") Long userId,
                                     @Valid @RequestBody PostDto postDto) {
        return postService.createPost(userId, postDto);
    }

    @PutMapping("/api/users/{user_id}/posts/{post_id}")
    public ResponseDto<Post> updatePost(@PathVariable(value = "user_id") Long userId,
                                        @PathVariable(value = "post_id") Long postId,
                                        @Valid @RequestBody String caption) {
        return postService.updatePost(userId, postId, caption);
    }

    @DeleteMapping("/api/users/{user_id}/posts/{post_id}")
    public ResponseDto<Object> deletePost(@PathVariable(value = "user_id") Long userId,
                                          @PathVariable(value = "post_id") Long postId) {
        return postService.deletePost(userId, postId);
    }








}
