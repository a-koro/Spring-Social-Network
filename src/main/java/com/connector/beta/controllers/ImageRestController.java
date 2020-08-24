package com.connector.beta.controllers;

import com.connector.beta.dto.UserDto;
import com.connector.beta.services.ImageServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ImageRestController {

    private final UserServiceInterface userService;
    private final ImageServiceInterface imageService;

    @Autowired
    public ImageRestController(UserServiceInterface userService, ImageServiceInterface imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> uploadImage(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getCurrentUser());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUserProfiles(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @PostMapping(
            path = "{userEmail}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userEmail") String userEmail,
                                       @RequestParam("file") MultipartFile file){

            imageService.uploadUserProfileImage(userEmail,file);
    }


}
