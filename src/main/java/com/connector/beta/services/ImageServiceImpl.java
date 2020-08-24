package com.connector.beta.services;

import com.connector.beta.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageServiceInterface{

    private final ImageRepo imageRepo;

    @Autowired
    public ImageServiceImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public void uploadUserProfileImage(String userEmail, MultipartFile file) {

    }
}
