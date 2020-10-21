package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.LikeDto;
import com.arpan.instagram.exception.*;
import com.arpan.instagram.model.Like;
import com.arpan.instagram.repository.LikeRepository;
import com.arpan.instagram.repository.PostRepository;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikeServiceImpl(PostRepository postRepository, LikeRepository likeRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Like likePost(Long postId, Long userId) {
        return userRepository.findById(userId)
            .map(user -> postRepository.findById(postId)
                    .map(post -> {
                        if (likeRepository.findByPostIdAndUserId(postId, userId).isPresent())
                            throw new ResourceConflictException("post "+postId+" already liked by user "+ userId);
                        else
                            return likeRepository.save(new Like(post, user));
                    })
                    .orElseThrow(() -> new PostNotFoundException(postId.toString())))
            .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @Override
    public List<Like> getLikesByPostId(Long postId) {
        return postRepository.findById(postId)
                .map(post -> likeRepository.findAllByPostId(postId))
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    }

    @Override
    public void dislikePost(Long postId, Long userId) {
        likeRepository.findByPostIdAndUserId(postId, userId)
            .map(like -> {
                    likeRepository.deleteById(like.getId());
                    return HttpStatus.OK;
                })
            .orElseThrow(() -> new LikeNotFoundException(userId+ " userId "+postId+" postId combo "));
    }
}
