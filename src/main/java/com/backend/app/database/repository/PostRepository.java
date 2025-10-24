package com.backend.app.database.repository;

import java.beans.Transient;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.app.database.entity.Post;

import jakarta.transaction.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post ORDER BY id DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Post> getDiscoverPosts(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = """
            SELECT *
            FROM post
            WHERE "by" IN (
                SELECT friend_id
                FROM friend
                WHERE user_id = (SELECT id FROM users WHERE username = :username)

                UNION

                SELECT user_id
                FROM friend
                WHERE friend_id = (SELECT id FROM users WHERE username = :username)
            )
            AND "by" <> (SELECT id FROM users WHERE username = :username)
            ORDER BY id DESC
            LIMIT :limit OFFSET :offset
                """, nativeQuery = true)
    List<Post> getFollowingPosts(
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE Post set star = star + 1 where id = :id")
    int increaseStar(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Post set star = star - 1 where id = :id")
    int decreaseStar(Long id);
}
