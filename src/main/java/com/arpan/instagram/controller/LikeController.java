package com.arpan.instagram.controller;

import com.arpan.instagram.dto.LikeDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.exception.BaseException;
import com.arpan.instagram.exception.UserNotFoundException;
import com.arpan.instagram.model.Like;
import com.arpan.instagram.repository.LikeRepository;
import com.arpan.instagram.service.LikeService;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/api/posts/{post_id}/like")
    public ResponseDto<Like> likePost(@PathVariable(value = "post_id") Long postId,
                                      @RequestBody LikeDto likeDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    201,
                    "Success",
                    Collections.singletonList(likeService.likePost(postId, likeDto.getUserId())));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage());
        }
    }

    @DeleteMapping("/api/posts/{post_id}/dislike")
    public ResponseDto<?> dislikePost(@PathVariable(value = "post_id") Long postId,
                                      @RequestBody LikeDto likeDto) {
        try {
            likeService.dislikePost(postId, likeDto.getUserId());
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    Collections.emptyList());
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage());
        }
    }

    @GetMapping("/api/posts/{post_id}/likes")
    public ResponseDto<Like> getLikesByPostId(@PathVariable(value = "post_id") Long postId) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    likeService.getLikesByPostId(postId));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage());
        }
    }
}
