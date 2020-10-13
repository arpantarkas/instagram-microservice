package com.arpan.instagram.controller;

import com.arpan.instagram.model.Post;
import com.arpan.instagram.service.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(@Qualifier("postServiceImpl") PostService postService) {
        this.postService = postService;
    }

//    @PostMapping("/api/posts")
//    public Post createPost (@RequestBody )





}
