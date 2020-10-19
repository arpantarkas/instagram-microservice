package com.arpan.instagram.controller;

import com.arpan.instagram.dto.CommentDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.model.Comment;
import com.arpan.instagram.repository.CommentRepository;
import com.arpan.instagram.service.CommentService;
import com.arpan.instagram.util.ResponseUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/comments/{id}")
    public ResponseDto<Comment> getCommentById(@PathVariable(value = "id") Long commentId) {
        return null;
    }

    @GetMapping("/api/posts/{post_id}/comments")
    public ResponseDto<Comment> getCommentsByPostId(@PathVariable(value = "post_id") Long postId) {
        return ResponseUtil.setSuccessResponse(200, "Success", commentService.getCommentsByPostId(postId));
    }

    @PostMapping("/api/posts/{post_id}/comments")
    public ResponseDto<Comment> createComment(@PathVariable(value = "post_id") Long postId,
                                               @RequestBody CommentDto commentDto) {
        return ResponseUtil.setSuccessResponse(201, "Success", Collections.singletonList(commentService.createComment(postId, commentDto).get()));
    }

    @DeleteMapping("/api/posts/{post_id}/comments/{comment_id}")
    public ResponseDto<?> deleteComment(@PathVariable(value = "post_id") Long postId,
                                        @PathVariable(value = "comment_id") Long commentId) {
        return ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(commentService.deleteComment(postId, commentId)));
    }

    @GetMapping("/api/posts/{post_id}/comments/{comment_id}")
    public ResponseDto<Comment> getRepliesByParentId(@PathVariable(value = "post_id") Long postId,
                                                     @PathVariable(value = "comment_id") Long commentId) {
        return ResponseUtil.setSuccessResponse(200, "Success", commentService.getRepliesByParentId(postId, commentId));
    }

    @PutMapping("/api/posts/{post_id}/comments/{comment_id}")
    public ResponseDto<Comment> updateComment(@PathVariable(value = "post_id") Long postId,
                                              @PathVariable(value = "comment_id") Long commentId,
                                              @RequestBody CommentDto commentDto) {
        return ResponseUtil.setSuccessResponse(200, "Success", Collections.singletonList(commentService.updateComment(commentDto.getMessage(), postId, commentId).get()));
    }
}
