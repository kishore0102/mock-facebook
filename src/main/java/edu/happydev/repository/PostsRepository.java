package edu.happydev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.happydev.entity.Posts;
import edu.happydev.model.PostsDto;

@Repository
public interface PostsRepository extends JpaRepository<Posts, String> {

    @Query("select new edu.happydev.model.PostsDto(p.id.postId, u.firstName, u.lastName, p.contentTxt, p.createdTs, " +
            "(case when pl2.id.email is null then false else true end) as userLiked, " +
            "count(distinct pl1.id.email) as likesCount, count(distinct pc.id.seqNbrP) as commentsCount) " +
            "from Posts p " +
            "left join Users u on (u.email = p.email) " +
            "left join PostsLikes pl1 on (pl1.id.postId = p.postId) " +
            "left join PostsComments pc on (pc.id.postId = p.postId) " +
            "left join PostsLikes pl2 on (pl2.id.email = :email and pl2.id.postId = p.postId) " +
            "where p.email in :friendsList " +
            "group by  p.id.postId, u.firstName, u.lastName, p.contentTxt, p.createdTs, userLiked " +
            "order by p.createdTs desc")
    List<PostsDto> getAllPostsSorted(@Param(value = "friendsList") List<String> friendsList, String email);

}
