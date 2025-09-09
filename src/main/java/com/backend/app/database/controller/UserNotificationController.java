package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.JwtUtil;
import com.backend.app.database.entity.User;
import com.backend.app.database.service.UserNotificationService;
import com.backend.app.database.service.UserService;
import com.backend.app.dto.UserDTO;
import com.backend.app.dto.UserNotificationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhot:3000")
public class UserNotificationController {
  UserNotificationService userNotificationService;
  UserService userService;

  public UserNotificationController(UserNotificationService userNotificationService, UserService userService) {
    this.userNotificationService = userNotificationService;
    this.userService = userService;
  }

  @Autowired
  JwtUtil jwt;

  @GetMapping("/user-notification")
  public UserNotificationResponseDTO getMethodName(@RequestHeader("authorization") String token) {
    UserNotificationResponseDTO res = new UserNotificationResponseDTO();
    if (jwt.validateToken(token)) {
      String email = jwt.extractEmail(token);
      res.setResponse("valid");
      res.setNotifications(userNotificationService.getNotification(email));
      User user = userService.getByEmail(email);
      UserDTO userDTO = new UserDTO();
      userDTO.setName(user.getName());
      userDTO.setUsername(user.getUsername());
      userDTO.setEmail(user.getEmail());
      userDTO.setImgurl(user.getImgurl());
      userDTO.setRoomCOde(user.getRoomCode());
      userDTO.setType(user.getType());
      res.setUserDTO(userDTO);
      return res;
    } else {
      res.setResponse("invalid");
      return res;
    }
  }

}
