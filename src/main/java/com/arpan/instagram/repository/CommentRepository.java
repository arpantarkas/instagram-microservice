package com.arpan.instagram.repository;

import com.arpan.instagram.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);
    List<Comment> findAllByPostIdAndParentId(Long postId, Long parentId);
}
