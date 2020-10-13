package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.PostDto;
import com.arpan.instagram.model.Post;
import com.arpan.instagram.repository.PostRepository;
import com.arpan.instagram.service.PostService;
import com.arpan.instagram.util.EntityMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostDto postDto) {
        return postRepository.save(EntityMapper.toPost(postDto));
    }

}
