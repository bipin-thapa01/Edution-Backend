package com.backend.app.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.app.database.entity.UserNotification;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,Long> {
  public List<UserNotification> findByUserId(Long userId);
}
