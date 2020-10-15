package com.arpan.instagram.repository;

import com.arpan.instagram.model.Post;
import com.arpan.instagram.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByUser(User user, Pageable pageable);
    Optional<Post> findByIdAndUserId(Long postId, Long userId);
}
