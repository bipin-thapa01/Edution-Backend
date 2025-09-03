package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.database.service.UserNotificationService;
import com.backend.app.dto.UserNotificationDTO;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhot:3000")
public class UserNotificationController {
  UserNotificationService userNotificationService;
  public UserNotificationController(UserNotificationService userNotificationService){
    this.userNotificationService = userNotificationService;
  }

  @GetMapping("/user-notification")
  public List<UserNotificationDTO> getMethodName(@RequestHeader("email") String email) {
      return userNotificationService.getNotification(email);
  }
  
}
