package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.exception.InvalidRequestException;
import com.arpan.instagram.exception.PostNotFoundException;
import com.arpan.instagram.exception.UserNotFoundException;
import com.arpan.instagram.model.Image;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.model.User;
import com.arpan.instagram.repository.ImageRepository;
import com.arpan.instagram.repository.PostRepository;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.service.PostService;
import com.arpan.instagram.util.EntityMapper;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Post> getPostsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId)
                .map(user -> postRepository.findAllByUser(user, pageable).getContent())
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @Override
    public Post getPostByIdAndUserId(Long userId, Long postId) {
        return postRepository.findByIdAndUserId(postId, userId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    }

    @Override
    public Post createPost(Long userId, PostDto postDto) {
        return userRepository.findById(userId)
                .map(user -> {
                    try {
                        Post post = postRepository.save(EntityMapper.toPost(postDto, user));
                        postDto.getImages().parallelStream().forEach(
                                image -> imageRepository.save(new Image(image, post))
                        );
                        return post;
                    } catch (Exception e) {
                        throw new InvalidRequestException(e.getMessage());
                    }

                }).orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @Override
    public Post updatePost(Long userId, Long postId, String caption) {
        return postRepository.findByIdAndUserId(postId, userId)
                .map(post -> {
                    post.setCaption(caption);
                    return post;
                })
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Optional<Post> post = postRepository.findByIdAndUserId(postId, userId);

        if (post.isPresent())
            postRepository.delete(post.get());
        else
            throw new PostNotFoundException(postId.toString());
    }
}
