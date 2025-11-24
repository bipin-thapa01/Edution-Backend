package com.backend.app.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import com.backend.app.database.entity.Friend;
import jakarta.transaction.Transactional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
  public List<Friend> findByUserId(Long user);

  @Modifying
  @Transactional
  @Query("UPDATE Friend f SET f.status = :status, f.date = :date where f.userId = :userId and f.friendId = :friendId")
  public int updateStatusAndDate(Long userId, Long friendId, String status, OffsetDateTime date);

  @Query("""
        SELECT f.id
        FROM Friend f
        WHERE (f.userId = :userId AND f.friendId = :friendId)
        OR (f.userId = :friendId AND f.friendId = :userId)
      """)
  Long isFriend(@Param("userId") Long userId, @Param("friendId") Long friendId);

  @Query("""
        SELECT f
        FROM Friend f
        WHERE (f.userId = :userId or f.friendId = :userId) and f.status = 'accepted'
      """)
  List<Friend> findFriendLists(@Param("userId") Long userId);

  @Query("""
    SELECT COUNT(f)
    FROM Friend f
    WHERE (f.status = 'accepted'
    AND (f.userId = :userId OR f.friendId = :userId))
""")
Long findFriendCount(@Param("userId") Long userId);

}
