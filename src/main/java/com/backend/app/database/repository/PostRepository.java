package com.backend.app.database.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.app.database.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post ORDER BY id DESC LIMIT ?1 OFFSET ?2", nativeQuery = true)
    public List<Post> getDiscoverPosts(int limit, int offset);
}

