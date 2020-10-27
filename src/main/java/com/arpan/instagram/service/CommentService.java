package com.arpan.instagram.service;

import com.arpan.instagram.dto.CommentDto;
import com.arpan.instagram.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Long postId, CommentDto commentDto);
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getRepliesByParentId(Long postId, Long parentId);
    Comment updateComment(String message, Long postId, Long commentId);
    void deleteComment(Long postId, Long commentId);


}
