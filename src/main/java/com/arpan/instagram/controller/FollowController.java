package com.arpan.instagram.controller;

import com.arpan.instagram.dto.FollowDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.exception.BaseException;
import com.arpan.instagram.model.Follow;
import com.arpan.instagram.service.FollowService;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/api/users/{user_id}/followers")
    public ResponseDto<Follow> getFollowers(@PathVariable(value = "user_id") Long userId) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    followService.getFollowers(userId)
            );
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @GetMapping("/api/users/{user_id}/following")
    public ResponseDto<Follow> getFollowing(@PathVariable(value = "user_id") Long userId) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    followService.getFollowing(userId)
            );
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @PostMapping("/api/follow")
    public ResponseDto<Follow> followUser (@Valid @RequestBody FollowDto followDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    Collections.singletonList(followService.followUser(followDto.getFollowedToId(), followDto.getFollowedById()))
            );
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/api/unfollow")
    public ResponseDto<Object> unfollowUser (@Valid @RequestBody FollowDto followDto) {
        try {
            followService.unfollowUser(followDto.getFollowedToId(), followDto.getFollowedById());
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Success",
                    Collections.emptyList()
            );
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }
}

