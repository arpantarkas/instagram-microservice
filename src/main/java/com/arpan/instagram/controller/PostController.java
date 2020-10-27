package com.arpan.instagram.controller;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.exception.BaseException;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.service.PostService;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(@Qualifier("postServiceImpl") PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/users/{user_id}/posts")
    public ResponseDto<Post> getPostsByUserId(@PathVariable(value = "user_id") Long userId,
                                              Pageable pageable) {
        try {
            return ResponseUtil.setSuccessResponse(200, "Success", postService.getPostsByUserId(userId, pageable));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }


    @GetMapping("/api/users/{user_id}/posts/{post_id}")
    public ResponseDto<Post> getPostById(@PathVariable(value = "user_id") Long userId,
                                         @PathVariable(value = "post_id") Long postId) {
        try {
            return ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(postService.getPostByIdAndUserId(userId, postId)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }


    @PostMapping("/api/users/{user_id}/posts")
    public ResponseDto<?> createPost(@PathVariable(value = "user_id") Long userId,
                                     @Valid @RequestBody PostDto postDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Post Created",
                    Collections.singletonList(postService.createPost(userId, postDto)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @PutMapping("/api/users/{user_id}/posts/{post_id}")
    public ResponseDto<Post> updatePost(@PathVariable(value = "user_id") Long userId,
                                        @PathVariable(value = "post_id") Long postId,
                                        @Valid @RequestBody String caption) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Post updated",
                    Collections.singletonList(postService.updatePost(userId, postId, caption)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/api/users/{user_id}/posts/{post_id}")
    public ResponseDto<?> deletePost(@PathVariable(value = "user_id") Long userId,
                                          @PathVariable(value = "post_id") Long postId) {
        try {
            postService.deletePost(userId, postId);
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Post Deleted",
                    Collections.emptyList());
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }








}
