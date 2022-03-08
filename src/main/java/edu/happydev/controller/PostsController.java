package edu.happydev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.happydev.model.PostsCommentsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.happydev.entity.Posts;
import edu.happydev.model.PostsDto;
import edu.happydev.service.PostsService;

@RestController
@RequestMapping("/api/posts/")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping(value = "/getPosts")
    public ResponseEntity<List<PostsDto>> getAllPosts(HttpServletRequest request) throws Exception {
        String email = request.getSession().getAttribute("email").toString();
        List<PostsDto> allPosts = postsService.getAllPosts(email);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PostMapping(value = "/addPost")
    public ResponseEntity<Posts> addNewPost(@RequestBody Posts newPost, HttpServletRequest request) throws Exception {
        String email = request.getSession().getAttribute("email").toString();
        newPost.setEmail(email);
        try {
            newPost = postsService.addNewPost(newPost);
            return new ResponseEntity<>(newPost, HttpStatus.OK);
        } catch (Exception ex) {
            throw new Exception("error adding a post");
        }
    }

    @GetMapping(value = "/getComments/{postId}")
    public ResponseEntity<List<PostsCommentsDto>> getCommentsForPost(@PathVariable String postId,
            HttpServletRequest request) throws Exception {
        List<PostsCommentsDto> comments = postsService.getAllCommentsForPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping(value = "/getLikes/{postId}")
    public ResponseEntity<List<String>> getLikesForPost(@PathVariable String postId, HttpServletRequest request)
            throws Exception {
        List<String> likes = postsService.getAllLikesForPost(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping(value = "/likePost/{postId}")
    public ResponseEntity<String> likePostByPostId(@PathVariable String postId, HttpServletRequest request)
            throws Exception {
        String email = request.getSession().getAttribute("email").toString();
        String response = postsService.likeOrUnlikePost(postId, email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/addComment/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void addCommentForPostId(@PathVariable String postId,
            @RequestBody String comment, HttpServletRequest request) throws Exception {
        String email = request.getSession().getAttribute("email").toString();
        postsService.addCommentForPostId(postId, email, comment);
    }

}
