package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.model.Image;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.model.User;
import com.arpan.instagram.repository.ImageRepository;
import com.arpan.instagram.repository.PostRepository;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.service.PostService;
import com.arpan.instagram.util.EntityMapper;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ResponseDto<Post> getPostsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId)
                .map(user -> ResponseUtil.setSuccessResponse(
                        200,
                        "Success",
                        postRepository.findAllByUser(user, pageable).getContent()
                )).orElseGet(() -> ResponseUtil.setErrorResponse(404, "User not found"));

    }

    @Override
    public ResponseDto<Post> getPostById(Long userId, Long postId) {
        return postRepository.findByIdAndUserId(postId, userId)
                .map(post -> ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(post))
                ).orElseGet(() -> ResponseUtil.setErrorResponse(404, "Post Not Found"));

        // This Works
//        return userRepository.findById(userId)
//                .map(user -> postRepository.findById(postId)
//                        .map(post -> ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(post)))
//                        .orElseGet(() -> ResponseUtil.setErrorResponse(404, "Post not found")))
//                .orElseGet(() -> ResponseUtil.setErrorResponse(404, "User not found"));

    }

    @Override
    public ResponseDto<?> createPost(Long userId, PostDto postDto) {
        return userRepository.findById(userId)
                .map(user -> {
                    try {
                        Post post = postRepository.save(EntityMapper.toPost(postDto, user));
                        postDto.getImages().parallelStream().forEach(
                                image -> imageRepository.save(new Image(image, post))
                        );
                        return ResponseUtil.setSuccessResponse(201, "Post Successfully created", Collections.singletonList(post));
                    } catch (Exception e) {
                        return ResponseUtil.setErrorResponse(400, e.getMessage());
                    }

                }).orElseGet(() -> ResponseUtil.setErrorResponse(404, "User doesn't exist"));

    }

    @Override
    public ResponseDto<Post> updatePost(Long userId, Long postId, String caption) {
        return postRepository.findByIdAndUserId(postId, userId)
                .map(post -> {
                            post.setCaption(caption);
                            return ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(postRepository.save(post)));
                        })
                .orElseGet(() -> ResponseUtil.setErrorResponse(404, "Post not found"));
//                .orElseGet(() -> ResponseUtil.setErrorResponse(404, "User not found"));

    }

    @Override
    public ResponseDto<Object> deletePost(Long userId, Long postId) {

        return postRepository.findById(postId)
                .map(post -> {
                    postRepository.delete(post);
                    return ResponseUtil.setSuccessResponse(200, "Success", Collections.emptyList());
                }).orElseGet(() -> ResponseUtil.setErrorResponse(404, "Post Not found"));

//        return userRepository.findById(userId)
//                .map(user -> postRepository.findById(postId)
//                        .map(post -> {
//                            postRepository.delete(post);
//                            return ResponseUtil.setSuccessResponse(200, "Success", Collections.emptyList());
//                        })
//                        .orElseGet(() -> ResponseUtil.setErrorResponse(404, "Post not found")))
//                .orElseGet(() -> ResponseUtil.setErrorResponse(404, "User not found"));


    }
}
