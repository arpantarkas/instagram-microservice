package com.arpan.instagram.repository;

import com.arpan.instagram.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
