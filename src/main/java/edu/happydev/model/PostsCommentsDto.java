package edu.happydev.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostsCommentsDto {

    String comment;
    String name;
    String createdTs;

    public PostsCommentsDto(String comment, String firstName, String lastName, LocalDateTime createdTs) {
        this.comment = comment;
        this.name = (firstName + " " + lastName).trim();
        this.createdTs = createdTs.toString();
    }

}
