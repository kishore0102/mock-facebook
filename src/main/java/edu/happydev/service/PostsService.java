package edu.happydev.service;

import java.util.List;
import java.util.Optional;

import edu.happydev.entity.PostsLikes;
import edu.happydev.model.PostsCommentsDto;
import edu.happydev.repository.PostsCommentsRepository;
import edu.happydev.repository.PostsLikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.happydev.entity.Posts;
import edu.happydev.model.PostsDto;
import edu.happydev.repository.PostsRepository;

@Service
public class PostsService {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsLikesRepository postsLikesRepository;

    @Autowired
    PostsCommentsRepository postsCommentsRepository;

    @Autowired
    FriendsService friendsService;

    public List<PostsDto> getAllPosts(String email) {
        List<String> friendsList = friendsService.getAllFriendsByEmail(email);
        friendsList.add(email);
        return postsRepository.getAllPostsSorted(friendsList, email);
    }

    public Posts addNewPost(Posts post) {
        return postsRepository.save(post);
    }

    public List<PostsCommentsDto> getAllCommentsForPost(String postId) {
        return postsCommentsRepository.getCommentsByPostId(postId);
    }

    public void addCommentForPostId(String postId, String email, String comment) {
        postsCommentsRepository.addCommentForPostId(postId, email, comment);
    }

    public List<String> getAllLikesForPost(String postId) {
        return postsLikesRepository.getLikesByPostId(postId);
    }

    public String likeOrUnlikePost(String postId, String emailId) {
        Optional<PostsLikes> ifPostsLikeExists = getPostLikeByPostIdAndEmail(postId, emailId);
        String response;
        if (ifPostsLikeExists.isPresent()) {
            postsLikesRepository.delete(ifPostsLikeExists.get());
            response = "unliked";
        } else {
            PostsLikes postLike = new PostsLikes(new PostsLikes.PostsLikesId(postId, emailId));
            postsLikesRepository.save(postLike);
            response = "liked";
        }
        return response;
    }

    public Optional<PostsLikes> getPostLikeByPostIdAndEmail(String postId, String email) {
        return postsLikesRepository.findById(new PostsLikes.PostsLikesId(postId, email));
    }

}
