package com.arpan.instagram.repository;

import com.arpan.instagram.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(Long postId);
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
}
