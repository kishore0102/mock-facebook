package edu.happydev.repository;

import edu.happydev.entity.PostsComments;
import edu.happydev.entity.PostsComments.PostsCommentsId;
import edu.happydev.model.PostsCommentsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostsCommentsRepository extends JpaRepository<PostsComments, PostsCommentsId> {

    @Query("select new edu.happydev.model.PostsCommentsDto(pc.comment, u.firstName, u.lastName, pc.createdTs) " +
            "from PostsComments pc " +
            "left join Users u on (u.id.email = pc.email) " +
            "where pc.id.postId = :postId " +
            "order by pc.id.seqNbrP asc")
    List<PostsCommentsDto> getCommentsByPostId(@Param("postId") String postId);

    @Transactional
    @Modifying
    @Query(value = "insert into facebook.posts_comments (post_id, seq_nbr_p, email, comment) " +
            "select :postId, COALESCE(max(seq_nbr_p) , 0) + 1, :email, :comment " +
            "from facebook.posts_comments pc where pc.post_id = :postId ", nativeQuery = true)
    void addCommentForPostId(@Param("postId") String postId, @Param("email") String email,
            @Param("comment") String comment);

}
