package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.CommentDto;
import com.arpan.instagram.model.Comment;
import com.arpan.instagram.repository.CommentRepository;
import com.arpan.instagram.repository.PostRepository;
import com.arpan.instagram.repository.UserRepository;
import com.arpan.instagram.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Comment> createComment(Long postId, CommentDto commentDto) {
        return userRepository.findById(commentDto.getUserId()).flatMap(user -> postRepository.findById(postId)
                .map(post -> {
                    Optional<Comment> parentComment = commentRepository.findById(commentDto.getParentId().orElse(-1L));
                    Comment comment = new Comment(commentDto.getMessage(), post, user, parentComment.orElse(null));
                    return Optional.of(commentRepository.save(comment));
        }).orElseGet(Optional::empty));
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @Override
    public List<Comment> getRepliesByParentId(Long postId, Long parentId) {
        return commentRepository.findAllByPostIdAndParentId(postId, parentId);
    }

    @Override
    public Optional<Comment> updateComment(String message, Long postId, Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> comment.setMessage(message));
    }

    @Override
    public Object deleteComment(Long postId, Long commentId) {

        return commentRepository.findByIdAndPostId(commentId, postId)
                .map(comment -> {
                        commentRepository.delete(comment);
                        return null;
                }).orElseGet(Optional::empty);
    }
}
