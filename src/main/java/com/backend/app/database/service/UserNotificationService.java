package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.app.database.entity.User;
import com.backend.app.database.entity.UserNotification;
import com.backend.app.database.repository.UserNotificationRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.UserNotificationDTO;

@Service
public class UserNotificationService {
  UserNotificationRepository userNotificationRepository;
  UserRepository userRepository;

  public UserNotificationService(UserNotificationRepository userNotificationRepository, UserRepository userRepository) {
    this.userNotificationRepository = userNotificationRepository;
    this.userRepository = userRepository;
  }

  public List<UserNotificationDTO> getNotification(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null)
      return new ArrayList<>();
    int i = 0;
    return userNotificationRepository.findByUserId(user.getId())
        .stream()
        .map(n -> {
          UserNotificationDTO dto = new UserNotificationDTO();
          dto.setDescription(n.getDescription());
          dto.setSource(n.getSource());
          dto.setDate(n.getDate());
          if(n.getSource().equals("admin")){
            dto.setImgurl("https://i.postimg.cc/J7PTgqcW/24bdeecd546a2c6b7e34857a104afe68.jpg");
          }
          else{
            dto.setImgurl(userRepository.findImgurlByUsername(n.getSource()));
          }
          return dto;
        })
        .toList();
  }
}
