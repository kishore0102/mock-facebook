package edu.happydev.model;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class PostsDto {

    String postId;
    String name;
    String contentTxt;
    String createdTs;
    Boolean userLiked;
    Long likesCount;
    Long commentsCount;

    public PostsDto(String postId, String firstName, String lastName, String contentTxt, LocalDateTime createdTs, Boolean userLiked, Long likesCount, Long commentsCount) {
        this.postId = postId;
        this.name = (firstName + " " + lastName).trim();
        this.contentTxt = contentTxt;
        this.createdTs = createdTs.toString();
        this.userLiked = userLiked;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }

}
