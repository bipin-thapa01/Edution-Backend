package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.UserNotificationRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.UserNotificationDTO;

@Service
public class UserNotificationService {
  UserNotificationRepository userNotificationRepository;
  UserRepository userRepository;
  FriendRepository friendRepository;

  public UserNotificationService(UserNotificationRepository userNotificationRepository, UserRepository userRepository,
      FriendRepository friendRepository) {
    this.userNotificationRepository = userNotificationRepository;
    this.userRepository = userRepository;
    this.friendRepository = friendRepository;
  }

  public List<UserNotificationDTO> getNotification(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null)
      return new ArrayList<>();
    List<UserNotificationDTO> notifications = new ArrayList<>(userNotificationRepository.findByUserId(user.getId())
        .stream()
        .map(n -> {
          UserNotificationDTO dto = new UserNotificationDTO();
          dto.setDescription(n.getDescription());
          dto.setSource(n.getSource());
          dto.setDate(n.getDate());
          dto.setType(userRepository.findTypeByUsername(n.getSource()));
          dto.setRequestType(n.getType());
          dto.setStatus(n.getStatus());
          dto.setImgurl(userRepository.findImgurlByUsername(n.getSource()));
          return dto;
        })
        .toList());// immutable list return gareko vara

    
    notifications.sort(Comparator.comparing(UserNotificationDTO::getDate).reversed());

    return notifications;
  }
}
