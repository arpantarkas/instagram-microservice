package com.arpan.instagram.service;

import com.arpan.instagram.dto.LikeDto;
import com.arpan.instagram.exception.PostNotFoundException;
import com.arpan.instagram.exception.UserNotFoundException;
import com.arpan.instagram.model.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Like likePost(Long postId, Long userId);
    List<Like> getLikesByPostId(Long postId);
    void dislikePost(Long postId, Long userId);
}
