package com.connector.beta.controllers;

import com.connector.beta.dto.UserDto;
import com.connector.beta.entities.Image;
import com.connector.beta.entities.ImageBackground;
import com.connector.beta.services.ImageServiceInterface;
import com.connector.beta.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
//@CrossOrigin("*")
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
            path = "/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@RequestParam("file") MultipartFile file){

           Integer userid= userService.findUserIdByEmail(userService.findCurrentUsername());
            imageService.uploadUserProfileImage(userid,file);
    }

    @GetMapping("/image/download")
    public ResponseEntity<Resource> downloadUserProfileImage(){

       Integer userId= userService
               .findUserIdByEmail(userService.findCurrentUsername());

        return getResourceResponseEntity(userId);

    }

    @PostMapping(
            path = "/image-background/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserBackgroundImage(@RequestParam("file") MultipartFile file){

        Integer userid= userService.findUserIdByEmail(userService.findCurrentUsername());
        imageService.uploadUserBackgroundImage(userid,file);
    }

    @GetMapping("/image-background/download")
    public ResponseEntity<Resource> downloadUserBackgroundImage(){
        Integer userId= userService
                .findUserIdByEmail(userService.findCurrentUsername());

        ImageBackground image = userService.findImageBackgroundFromUserId(userId);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=" + image.getTitle());

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(image.getFile().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(image.getFile()));
    }

    @GetMapping("/image-background/download/{id}")

    public ResponseEntity<Resource> downloadUserBackgroundImage(@PathVariable int id){

        ImageBackground image = userService.findImageBackgroundFromUserId(id);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=" + image.getTitle());

        return ResponseEntity.ok()
                .headers(header)

                .contentLength(image.getFile().length)

                .contentType(MediaType.parseMediaType("application/octet-stream"))

                .body(new ByteArrayResource(image.getFile()));
    }

    @GetMapping("/searchUsers/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Integer id) {
        return getResourceResponseEntity(id);
    }

    private ResponseEntity<Resource> getResourceResponseEntity(@PathVariable Integer id) {
        Image image = userService.findImageProfileFromUserId(id);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=" + image.getTitle());

        return  ResponseEntity.ok()
                .headers(header)
                .contentLength(image.getFile().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(image.getFile()));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getCurrentProfile(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getCurrentUser(id));
    }




}
