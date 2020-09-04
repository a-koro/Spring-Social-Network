package com.connector.beta.services;

import com.connector.beta.dto.UserProfileImageDto;
import com.connector.beta.entities.MyUser;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileServiceInterface {

    void uploadUserProfileImage(Integer userid, MultipartFile file);

    UserProfileImageDto findImageProfileFromUserId (Integer userId);

    void updateUserProfileImage(UserProfileImageDto dto);

    byte[] downloadUserProfileImage(Integer userid);
}
