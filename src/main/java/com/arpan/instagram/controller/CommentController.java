package com.arpan.instagram.controller;

import com.arpan.instagram.dto.CommentDto;
import com.arpan.instagram.dto.ResponseDto;
import com.arpan.instagram.exception.BaseException;
import com.arpan.instagram.model.Comment;
import com.arpan.instagram.service.CommentService;
import com.arpan.instagram.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try {
            return ResponseUtil.setSuccessResponse(200, "Success", commentService.getCommentsByPostId(postId));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @PostMapping("/api/posts/{post_id}/comments")
    public ResponseDto<Comment> createComment(@PathVariable(value = "post_id") Long postId,
                                               @RequestBody CommentDto commentDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Comment Created",
                    Collections.singletonList(commentService.createComment(postId, commentDto)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/api/posts/{post_id}/comments/{comment_id}")
    public ResponseDto<?> deleteComment(@PathVariable(value = "post_id") Long postId,
                                        @PathVariable(value = "comment_id") Long commentId) {
        try {
            commentService.deleteComment(postId, commentId);
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Comment Deleted",
                    Collections.emptyList());
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @GetMapping("/api/posts/{post_id}/comments/{comment_id}")
    public ResponseDto<Comment> getRepliesByParentId(@PathVariable(value = "post_id") Long postId,
                                                     @PathVariable(value = "comment_id") Long commentId) {
        try {
            return ResponseUtil.setSuccessResponse(200, "Success", commentService.getRepliesByParentId(postId, commentId));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }

    @PutMapping("/api/posts/{post_id}/comments/{comment_id}")
    public ResponseDto<Comment> updateComment(@PathVariable(value = "post_id") Long postId,
                                              @PathVariable(value = "comment_id") Long commentId,
                                              @RequestBody CommentDto commentDto) {
        try {
            return ResponseUtil.setSuccessResponse(
                    200,
                    "Post updated",
                    Collections.singletonList(commentService.updateComment(commentDto.getMessage(), postId, commentId)));
        } catch (BaseException e) {
            return ResponseUtil.setErrorResponse(
                    e.getHttpStatus().value(),
                    e.getMessage()
            );
        }
    }
}
