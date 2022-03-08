package edu.happydev.repository;

import java.util.List;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.happydev.entity.Friends;
import edu.happydev.entity.Friends.FriendsId;
import edu.happydev.model.FriendsDto;
import edu.happydev.model.FriendsSearch;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, FriendsId> {

        @Query("select case when id.friend1 = :email then id.friend2 when id.friend2 = :email then id.friend1 end " +
                        "from Friends where (id.friend1 = :email or id.friend2 = :email) and status = 'Y' ")
        List<String> getAllFriendsByEmail(@Param("email") String email);

        @Query("select new edu.happydev.model.FriendsDto( " +
                        "   (case when f.id.friend1 = :email then f.id.friend2 " +
                        "   when f.id.friend2 = :email then f.id.friend1 end) as friendEmail, " +
                        "   f.status, u.firstName, u.lastName, u.bio, f.id.friend2) " +
                        "from Friends f " +
                        "left join Users u on (u.id.email = (case when f.id.friend1 = :email then f.id.friend2 " +
                        "       when f.id.friend2 = :email then f.id.friend1 end) ) " +
                        "where (f.id.friend1 = :email or f.id.friend2 = :email) and " +
                        "(f.status = 'Y' or (f.status = 'N')) " +
                        "order by f.status asc")
        List<FriendsDto> getAllFriendsDetailsByEmail(@Param("email") String email);

        @Query("select count(f) from Friends f " +
                        "where (f.id.friend1 = :friend2 and f.id.friend2 = :friend1) or " +
                        " (f.id.friend1 = :friend1 and f.id.friend2 = :friend2) ")
        Integer checkFriendsPairExists(@Param("friend1") String friend1, @Param("friend2") String friend2);

        @Query("select new edu.happydev.model.FriendsSearch(u.firstName, u.lastName, u.bio, u.id.email, " +
                        "(case when f.status = 'Y' then 'Friends' " +
                        "       when f.status is null then 'Add Friend' " +
                        "       when f.status = 'N' and f.id.friend2 = :email then 'Pending in your queue' " +
                        "       when f.status = 'N' and f.id.friend1 = :email then 'Request sent' " +
                        "end) as status ) " +
                        "from Users u " +
                        "left join Friends f on ((f.id.friend1 = :email and f.id.friend2 = u.id.email) or " +
                        "                       (f.id.friend1 = u.id.email and f.id.friend2 = :email)) " +
                        "where (lower(u.firstName) = lower(:keyword) or lower(u.lastName) = lower(:keyword) or " +
                        "       lower(concat(u.firstName, ' ', u.lastName)) = lower(:keyword)) " +
                        "       and u.id.email <> :email ")
        List<FriendsSearch> searchFriends(@Param("keyword") String keyword, @Param("email") String email);

        @Transactional
        @Modifying
        @Query("delete from Friends f " +
                        "where (f.id.friend1 = :usersEmail and f.id.friend2 = :friendEmail) or " +
                        "       (f.id.friend1 = :friendEmail and f.id.friend2 = :usersEmail) ")
        void unfriend(@Param("usersEmail") String usersEmail, @Param("friendEmail") String friendEmail);

        @Query(value = "select distinct u.first_name, u.last_name, u.bio, u.email, " +
                        "(case when f4.status = 'Y' then 'Friends' " +
                        " when f4.status is null then 'Add Friend' " +
                        " when f4.status = 'N' and f4.friend2 = :email then 'Pending in your queue' " +
                        " when f4.status = 'N' and f4.friend1 = :email then 'Request sent' " +
                        "end) as status from " +
                        "(select (case when f1.friend1 = f2.friend_email then f1.friend2 " +
                        "when f1.friend2 = f2.friend_email then f1.friend1 end) as mutual_email " +
                        "from facebook.friends f1, " +
                        "(select (case when friend1 =:email then friend2 " +
                        "when friend2 = :email then friend1 end) as friend_email " +
                        "from facebook.friends f " +
                        "where f.friend1 = :email or f.friend2 = :email ) f2 " +
                        "where f1.friend1 = f2.friend_email or f1.friend2 = f2.friend_email ) f3 " +
                        "left join facebook.users u on (u.email = f3.mutual_email) " +
                        "left join facebook.friends f4 on ((f4.friend1 = :email and f4.friend2 = f3.mutual_email) or " +
                        " (f4.friend1 = f3.mutual_email and f4.friend2 = :email)) " +
                        "where f3.mutual_email not in ( " +
                        "select (case when friend1 = :email then friend2 " +
                        "when friend2 = :email then friend1 end) as friend_email " +
                        "from facebook.friends f " +
                        "where f.friend1 = :email or f.friend2 = :email) " +
                        "and f3.mutual_email <> :email ", nativeQuery = true)
        List<Tuple> suggestFriends(@Param("email") String email);

}
