package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.Friend;
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

  public UserNotificationService(UserNotificationRepository userNotificationRepository, UserRepository userRepository, FriendRepository friendRepository) {
    this.userNotificationRepository = userNotificationRepository;
    this.userRepository = userRepository;
    this.friendRepository = friendRepository;
  }

  public List<UserNotificationDTO> getNotification(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null)
      return new ArrayList<>();
    int i = 0;
    List<UserNotificationDTO> notifications = new ArrayList<>(userNotificationRepository.findByUserId(user.getId())
    .stream()
    .map(n -> {
      UserNotificationDTO dto = new UserNotificationDTO();
      dto.setDescription(n.getDescription());
      dto.setSource(n.getSource());
      dto.setDate(n.getDate());
      dto.setType(n.getType());
      if(n.getSource().equals("admin")){
        dto.setImgurl("https://i.postimg.cc/J7PTgqcW/24bdeecd546a2c6b7e34857a104afe68.jpg");
      }
      else{
        dto.setImgurl(userRepository.findImgurlByUsername(n.getSource()));
      }
      return dto;
    })
    .toList());//immutable list return gareko vara

    List<UserNotificationDTO> friendNotifications = friendRepository.findByUserId(user.getId()).stream().map(n -> {
      UserNotificationDTO dto = new UserNotificationDTO();
      String friendEmail = userRepository.findEmailById(n.getId());
      User u = userRepository.findByEmail(friendEmail);
      dto.setDescription("You have received follow request from " + u.getName());
      dto.setDate(n.getDate());
      dto.setImgurl(u.getImgurl());
      dto.setSource(u.getUsername());
      dto.setType("friend request");
      return dto;
    }).toList();
    
    notifications.addAll(friendNotifications);
    notifications.sort(Comparator.comparing(UserNotificationDTO::getDate).reversed());

    return notifications;
  }
}
