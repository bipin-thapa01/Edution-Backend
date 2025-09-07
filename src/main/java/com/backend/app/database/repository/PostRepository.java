package com.backend.app.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.app.database.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
  
}
