package edu.happydev.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private String postId;

    @Column(name = "email")
    private String email;

    @Column(name = "content_txt")
    private String contentTxt;

    @Column(name = "created_ts", insertable = false)
    private LocalDateTime createdTs;

}
