package com.connector.beta.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageServiceInterface {
    void uploadUserProfileImage(String userEmail, MultipartFile file);
}
