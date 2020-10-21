package com.arpan.instagram.service.impl;

import com.arpan.instagram.exception.ResourceConflictException;
import com.arpan.instagram.exception.UserNotFoundException;
import com.arpan.instagram.model.Follow;
import com.arpan.instagram.model.User;
import com.arpan.instagram.repository.FollowRepository;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public FollowServiceImpl(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @Override
    public List<Follow> getFollowers(Long followedToId) {
        return userRepository.findById(followedToId)
                .map(user -> followRepository.findAllByFollowedToId(followedToId))
                .orElseThrow(() -> new UserNotFoundException(followedToId.toString()));
    }

    @Override
    public List<Follow> getFollowing(Long followedById) {
        return userRepository.findById(followedById)
                .map(user -> followRepository.findAllByFollowedById(followedById))
                .orElseThrow(() -> new UserNotFoundException(followedById.toString()));
    }

    @Override
    public Follow followUser(Long followedToId, Long followedById) {

        List<Optional<User>> users = checkUserExists(followedToId, followedById);

        if (followRepository.findByFollowedToIdAndFollowedById(followedToId, followedById).isPresent())
            throw new ResourceConflictException(followedToId+" user is already followed by "+followedById);

        else
            return followRepository.save(new Follow(users.get(0).get(), users.get(1).get()));
    }

    @Override
    public void unfollowUser(Long followedToId, Long followedById) {

        checkUserExists(followedToId, followedById);
        Optional<Follow> followOptional = followRepository.findByFollowedToIdAndFollowedById(followedToId, followedById);
        if (followOptional.isPresent())
            followRepository.delete(followOptional.get());

        else
            throw new ResourceConflictException(followedToId+" user is not followed by "+followedById);

    }

    private List<Optional<User>> checkUserExists(Long followedToId, Long followedById) {
        Optional<User> followedToUser = userRepository.findById(followedToId);
        Optional<User> followedByUser = userRepository.findById(followedById);

        if (followedToUser.isEmpty())
            throw new UserNotFoundException(followedToId.toString());
        else if (followedByUser.isEmpty())
            throw new UserNotFoundException(followedById.toString());
        else
            return Arrays.asList(followedToUser, followedByUser);
    }
}
