package com.backend.app.database.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.backend.app.database.entity.Star;

import jakarta.transaction.Transactional;

@Repository
public interface StarRepository extends JpaRepository<Star,Long>{

  @Query("SELECT id from Star where postId = :postId and userId = :userId")
  Long isStarred(Long postId, Long userId);

  @Modifying
  @Transactional
  @Query("Insert INTO Star(postId, userId) values(:postId, :userId)")
  int addStar(Long postId, Long userId);

  @Modifying
  @Transactional
  @Query("DELETE FROM Star WHERE postId = :postId and userId = :userId")
  int removeStar(Long postId, Long userId);
}
