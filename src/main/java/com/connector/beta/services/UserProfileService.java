package com.connector.beta.services;




import com.connector.beta.bucket.BucketName;
import com.connector.beta.dto.UserProfileImageDto;
import com.connector.beta.entities.MyUser;
import com.connector.beta.filestore.FileStore;
import com.connector.beta.mapper.UserMapper;
import com.connector.beta.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


import static org.apache.http.entity.ContentType.*;

@Service
@Transactional
public class UserProfileService implements UserProfileServiceInterface {


    private final UserRepo userRepo;
    private final UserServiceInterface userService;
    private final UserMapper userMapper;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserRepo userRepo, UserServiceInterface userService
            , UserMapper userMapper, FileStore fileStore) {
        this.userRepo = userRepo;

        this.userService = userService;
        this.userMapper = userMapper;
        this.fileStore = fileStore;
    }

    @Override
    public void uploadUserProfileImage(Integer userid, MultipartFile file) {

        isEmpty(file);

        isImage(file);

        Map<String, String> metadata = extractMetadata(file);

      String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(),userid);

    String filename = String.format("%s-%s",file.getOriginalFilename(), UUID.randomUUID());

        try{
            fileStore.saveToS3(path,filename,
                    Optional.of(metadata),file.getInputStream());

            UserProfileImageDto dto = findImageProfileFromUserId(userid);
                dto.setUserProfileImageLink(filename);
            updateUserProfileImage(dto);

        }catch (IOException ex){
            throw new IllegalStateException(ex);
        }


    }

    @Override
    public UserProfileImageDto findImageProfileFromUserId(Integer userId) {


        UserProfileImageDto dto =userRepo.findImageProfileFromUserId(userId)
                .orElseThrow(()->
                        new IllegalStateException("User not found"));
     return dto;
    }

    @Override
    public void updateUserProfileImage(UserProfileImageDto dto) {
        MyUser user = userRepo.findById(dto.getUserId())
                .orElseThrow(()->
                        new IllegalStateException("user not found"));
        userMapper.updateCustomerFromDto(dto, user);
        userRepo.save(user);
    }

    @Override
    public byte[] downloadUserProfileImage(Integer userid) {
        UserProfileImageDto dto = findImageProfileFromUserId(userid);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                userid);
        Optional<String> userProfileImageLink = Optional.ofNullable(dto.getUserProfileImageLink());
       return userProfileImageLink
                .map(key->fileStore.download(path,key))
                .orElse(new byte[0]);
    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image["+file.getContentType()+"]");
        }
    }

    private void isEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("empty image");
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }



}
