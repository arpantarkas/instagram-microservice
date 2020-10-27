package com.arpan.instagram.service.impl;

import com.arpan.instagram.dto.CommentDto;
import com.arpan.instagram.exception.InvalidRequestException;
import com.arpan.instagram.exception.PostNotFoundException;
import com.arpan.instagram.exception.UserNotFoundException;
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

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment createComment(Long postId, CommentDto commentDto) {
        return userRepository.findById(commentDto.getUserId())
                .map(user -> postRepository.findById(postId)
                    .map(post -> {
                        Optional<Comment> parentComment = commentRepository.findById(commentDto.getParentId().orElse(-1L));
                        Comment comment = new Comment(commentDto.getMessage(), post, user, parentComment.orElse(null));
                        return commentRepository.save(comment);
                    }).orElseThrow(() -> new PostNotFoundException(postId.toString()))
                ).orElseThrow(() -> new UserNotFoundException(commentDto.getUserId().toString()));
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return postRepository.findById(postId)
            .map(post -> commentRepository.findAllByPostId(postId))
            .orElseThrow(() -> new PostNotFoundException(postId.toString()));
    }

    @Override
    public List<Comment> getRepliesByParentId(Long postId, Long parentId) {
        return commentRepository.findAllByPostIdAndParentId(postId, parentId);
    }

    @Override
    public Comment updateComment(String message, Long postId, Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .map(comment -> comment.setMessage(message))
                .orElseThrow(() -> new InvalidRequestException("Comment"+commentId.toString()+" not found"));
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Optional<Comment> comment = commentRepository.findByIdAndPostId(commentId, postId);

        if (comment.isPresent())
            commentRepository.delete(comment.get());
        else
            throw new InvalidRequestException("Comment "+commentId.toString()+ " not found");
    }
}
