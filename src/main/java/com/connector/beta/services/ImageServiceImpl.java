package com.connector.beta.services;

import com.connector.beta.entities.Image;
import com.connector.beta.entities.ImageBackground;
import com.connector.beta.entities.MyUser;
import com.connector.beta.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

        isEmpty(file);

        isImage(file);

        MyUser myUser= userService.findById(userid);


        Image image = new Image();
        image.setTitle(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setSize(String.valueOf(file.getSize()));
        try{
                image.setFile(file.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }


        myUser.setImage(image);
        userService.userSave(myUser);

    }

    @Override
    public void uploadUserBackgroundImage(Integer userid, MultipartFile file) {

        isEmpty(file);

        isImage(file);

        MyUser myUser= userService.findById(userid);


        ImageBackground image = new ImageBackground();
        image.setTitle(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setSize(String.valueOf(file.getSize()));
        try{
            image.setFile(file.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }


        myUser.setImageBackground(image);
        userService.userSave(myUser);
    }

    public void isImage(MultipartFile file) {
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image["+file.getContentType()+"]");
        }
    }

    public void isEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("empty image");
        }
    }

    @Override
    public void saveImageProfile(Image image) {
        imageRepo.save(image);
    }




}
