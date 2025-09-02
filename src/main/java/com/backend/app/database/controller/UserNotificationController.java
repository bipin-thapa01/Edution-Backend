package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.database.service.UserNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UserNotificationController {
  UserNotificationService userNotificationService;
  public UserNotificationController(UserNotificationService userNotificationService){
    this.userNotificationService = userNotificationService;
  }

  @GetMapping("/user-notification")
  public String getMethodName(@RequestHeader("email") String email) {
      return new String();
  }
  
}
