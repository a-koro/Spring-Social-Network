package com.connector.beta.services;

import com.connector.beta.entities.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageServiceInterface {
    void uploadUserProfileImage(Integer userid, MultipartFile file);
    void uploadUserBackgroundImage(Integer userid, MultipartFile file);

    void saveImageProfile(Image image);

    void isEmpty(MultipartFile file);
    void isImage(MultipartFile file);
}
