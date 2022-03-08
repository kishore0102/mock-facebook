package edu.happydev.repository;

import edu.happydev.entity.PostsLikes;
import edu.happydev.entity.PostsLikes.PostsLikesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsLikesRepository extends JpaRepository<PostsLikes, PostsLikesId> {

    @Query("select concat(u.firstName, ' ' , u.lastName)" +
            "from PostsLikes pl " +
            "left join Users u on (u.id.email = pl.id.email) " +
            "where pl.id.postId = :postId ")
    List<String> getLikesByPostId(String postId);

}
