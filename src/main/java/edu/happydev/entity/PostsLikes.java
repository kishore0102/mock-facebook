package edu.happydev.entity;

import javax.persistence.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "posts_likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsLikes {

    @EmbeddedId
    private PostsLikesId id;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostsLikesId implements Serializable {

        @Column(name = "post_id")
        private String postId;

        @Column(name = "email")
        private String email;

    }

}
