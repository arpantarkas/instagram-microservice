package com.arpan.instagram.service;

import com.arpan.instagram.model.Follow;

import java.util.List;

public interface FollowService {
    List<Follow> getFollowers(Long followedToId);
    List<Follow> getFollowing(Long followedById);
    Follow followUser(Long followedToId, Long followedById);
    void unfollowUser(Long followedToId, Long followedById);
}
