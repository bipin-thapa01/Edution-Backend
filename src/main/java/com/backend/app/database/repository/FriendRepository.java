package com.backend.app.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import com.backend.app.database.entity.Friend;
import jakarta.transaction.Transactional;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
  public List<Friend> findByUserId(Long user);

  @Modifying
  @Transactional
  @Query("UPDATE Friend f SET f.status = :status, f.date = :date where f.userId = :userId and f.friendId = :friendId")
  public int updateStatusAndDate(Long userId, Long friendId, String status, OffsetDateTime date);
}
