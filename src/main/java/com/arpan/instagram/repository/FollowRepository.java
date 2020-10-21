package com.arpan.instagram.repository;

import com.arpan.instagram.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowedToIdAndFollowedById(Long followedToId, Long followedById);
    List<Follow> findAllByFollowedToId(Long followedToId);
    List<Follow> findAllByFollowedById(Long followedById);
}
