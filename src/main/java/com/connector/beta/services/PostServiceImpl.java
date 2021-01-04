package com.connector.beta.services;

import com.connector.beta.dto.PostDto;
import com.connector.beta.projections.PostProjection;
import com.connector.beta.entities.*;
import com.connector.beta.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostServiceInterface {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    CheerRepo cheerRepo;

    @Autowired
    PostImageRepo postImageRepo;

    @Autowired
    ImageServiceInterface imageServiceInterface;

    @Override
    public List<Post> findByUser(Integer userId) {
        return postRepo.findByUserUserIdOrderByCreatedDesc(userId);
    }

    @Override
    public List<Post> findByUserIds(List<Integer> userIds) {
        List<Post> list = postRepo.findByUserUserIdInOrderByCreatedDesc(userIds);
        return list;
    }

    @Override
    public Post findPostByPostId(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("No data!"));
        return post;
    }

    @Transactional
    @Override
    public void updatePost(Post post) {
        postRepo.save(post);
    }

    @Transactional
    @Override
    public void insertPost(Post post) {
        postRepo.save(post);
    }

    @Transactional
    @Override
    public void insertPostWithImage(String text, MultipartFile file, MyUser user) {

        imageServiceInterface.isEmpty(file);
        imageServiceInterface.isImage(file);

        PostImage postImage = new PostImage();
        postImage.setTitle(file.getOriginalFilename());
        postImage.setType(file.getContentType());
        postImage.setSize(String.valueOf(file.getSize()));
        try{
            postImage.setFile(file.getBytes());
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Post post = new Post();
        post.setText(text);
        post.setImageUrl("BLOB");
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUser(user);
        postImage.setPost(post);

        postImageRepo.save(postImage);
    }

    @Override
    public PostImage findPostImageByPostId(int postId) {
        return postImageRepo.findByPostPostId(postId);
    }

    @Transactional
    @Override
    public List<Cheer> cheers(Post post, MyUser user) {
        Optional<Cheer> cheer = cheerRepo.findByUserAndPost(user, post);
        if(cheer.isPresent()) {
            Cheer oldCheer = cheer.get();
            oldCheer.setActive(!oldCheer.getActive());
        }
        else {
            Cheer newCheer = new Cheer();
            newCheer.setActive(true);
            newCheer.setUser(user);
            newCheer.setPost(post);
            cheerRepo.save(newCheer);
        }
        return cheerRepo.findByPost(post);
    }

    @Override
    public List<PostProjection> findByUserIdsTEST() {
        List<Integer> listOfNums = new ArrayList<>();
        listOfNums.add(1);
        listOfNums.add(2);
        List<PostProjection> list = postRepo.findAllByUserUserIdIn(listOfNums);
        return list;
    }

    @Override
    public void removePost(Post post) {
        postRepo.delete(post);
    }

    @Override
    public int getAllPosts() {
        return postRepo.getLengthOfPosts();
    }

    @Override
    public Post getSpecificPostFromCommentId(Integer commentId) throws NullPointerException {
        Post post = postRepo.findByCommentsCommentId(commentId).orElseThrow(() -> new NullPointerException("Post not found"));
        return post;
    }

    @Override
    public List<Post> findByUserIdsAndByPage(List<Integer> userIds, Integer page) {
        Pageable pageWithFiveElements = PageRequest.of(page,5);
        return postRepo.findByUserUserIdInOrderByCreatedDesc(userIds, pageWithFiveElements);
    }
}
