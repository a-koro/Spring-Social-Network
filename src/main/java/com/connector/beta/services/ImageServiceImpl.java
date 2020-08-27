package com.connector.beta.services;

import com.connector.beta.entities.Image;
import com.connector.beta.entities.MyUser;
import com.connector.beta.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import static org.apache.http.entity.ContentType.*;

@Service
@Transactional
public class ImageServiceImpl implements ImageServiceInterface{

    private final ImageRepo imageRepo;
    private final UserServiceInterface userService;

    @Autowired
    public ImageServiceImpl(ImageRepo imageRepo,
                            UserServiceInterface userService) {
        this.imageRepo = imageRepo;
        this.userService = userService;
    }

    @Override
    public void uploadUserProfileImage(Integer userid, MultipartFile file) {

        if(file.isEmpty()){
            throw new IllegalStateException("empty image");
        }

        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image["+file.getContentType()+"]");
        }

       MyUser myUser= userService.findById(userid);

        Map<String,String> metadata = new HashMap<>();

        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        Image image = new Image();
        image.setUser(myUser);
        image.setTitle(file.getOriginalFilename());
        try{
                image.setFile(file.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }

        saveImageProfile(image);

    }

    @Override
    public void saveImageProfile(Image image) {
        imageRepo.save(image);
    }




}
