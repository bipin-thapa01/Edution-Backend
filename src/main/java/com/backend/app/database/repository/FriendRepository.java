package com.backend.app.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.backend.app.database.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
  public List<Friend> findByUserdId(Long user);
}
