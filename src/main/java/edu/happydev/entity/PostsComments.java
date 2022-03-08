package edu.happydev.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsComments {

    @EmbeddedId
    private PostsCommentsId id;

    @Column(name = "seq_nbr_c")
    private String seqNbrC;

    @Column(name = "comment")
    private String comment;

    @Column(name = "email")
    private String email;

    @Column(name = "created_ts")
    private LocalDateTime createdTs;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostsCommentsId implements Serializable {

        @Column(name = "post_id")
        private String postId;

        @Column(name = "seq_nbr_p")
        private String seqNbrP;

    }

}
