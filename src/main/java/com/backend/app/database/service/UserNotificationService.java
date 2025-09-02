package com.backend.app.database.service;

import org.springframework.stereotype.Service;
import com.backend.app.database.repository.UserNotificationRepository;

@Service
public class UserNotificationService {
  UserNotificationRepository userNotificationRepository;
  public UserNotificationService(UserNotificationRepository userNotificationRepository){
    this.userNotificationRepository = userNotificationRepository;
  }


}
