package com.arpan.instagram.service.impl;

import com.arpan.instagram.model.Image;
import com.arpan.instagram.repository.ImageRepository;
import com.arpan.instagram.service.ImageService;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }
}
