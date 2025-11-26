package com.backend.app.database.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.Friend;
import com.backend.app.database.entity.User;
import com.backend.app.database.entity.UserNotification;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.UserNotificationRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.FriendRequestChangeDTO;
import com.backend.app.dto.FriendRequestDTO;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class FriendService {
  FriendRepository friendRepository;
  UserRepository userRepository;
  UserNotificationRepository userNotificationRepository;

  public FriendService(FriendRepository friendRepository, UserRepository userRepository,
      UserNotificationRepository userNotificationRepository) {
    this.friendRepository = friendRepository;
    this.userRepository = userRepository;
    this.userNotificationRepository = userNotificationRepository;
  }

  public List<User> getFriendRequests(String email) {
    User user = userRepository.findByEmail(email);
    List<Friend> friends = friendRepository.findByUserId(user.getId());
    List<Long> l = new ArrayList<>();
    for (Friend f : friends) {
      l.add(f.getFriendId());
    }
    return userRepository.findAllById(l);
  }

  public List<OffsetDateTime> getDate(String email) {
    User user = userRepository.findByEmail(email);
    List<Friend> friends = friendRepository.findByUserId(user.getId());
    List<OffsetDateTime> date = new ArrayList<>();
    for (Friend friend : friends) {
      date.add(friend.getDate());
    }
    return date;
  }

  public ResponseDTO sendRequestService(String username, String friendUsername) {
    Friend friend = new Friend();
    friend.setUserId(userRepository.findIdByUsername(username));
    friend.setFriendId(userRepository.findIdByUsername(friendUsername));
    friend.setDate(OffsetDateTime.now(ZoneOffset.UTC));
    friend.setStatus("pending");
    friendRepository.save(friend);
    ResponseDTO responseDTO = new ResponseDTO();
    responseDTO.setResponse("success");
    UserNotification userNotification = new UserNotification();
    userNotification.setDate(OffsetDateTime.now(ZoneOffset.UTC));
    userNotification.setUserId(userRepository.findIdByUsername(username));
    userNotification.setDescription("Request successfully sent to user @" + friendUsername);
    userNotification.setSource("admin");
    userNotification.setType("admin");
    userNotification.setStatus("pending");
    userNotificationRepository.save(userNotification);
    return responseDTO;
  }

  public ResponseDTO updateFriendTable(String username, String friendUsername, String response) {
    Long userId = userRepository.findIdByUsername(username);
    Long friendId = userRepository.findIdByUsername(friendUsername);
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    int status = friendRepository.updateStatusAndDate(userId, friendId, response, now);
    ResponseDTO responseDTO = new ResponseDTO();
    if (status == 1) {
      responseDTO.setResponse("success");
    } else {
      responseDTO.setResponse("error");
    }
    return responseDTO;
  }

  public ResponseDTO unfriendService(FriendRequestChangeDTO friendDTO) {
    Long userId = userRepository.findIdByUsername(friendDTO.getUsername());
    Long friendId = userRepository.findIdByUsername(friendDTO.getFriendUsername());
    int n = friendRepository.deleteFriendRequest(userId, friendId);
    ResponseDTO responseDTO = new ResponseDTO();
    if (n == 1) {
      responseDTO.setResponse("success");
      UserNotification userNotification = new UserNotification();
      userNotification.setDate(OffsetDateTime.now(ZoneOffset.UTC));
      userNotification.setUserId(userId);
      userNotification
          .setDescription("Request sent to user @" + friendDTO.getFriendUsername() + " is deleted successfully");
      userNotification.setSource("admin");
      userNotification.setType("admin");
      userNotification.setStatus("pending");
      userNotificationRepository.save(userNotification);
    } else {
      responseDTO.setResponse("unsuccess");
    }
    return responseDTO;
  }

  public FriendRequestDTO fetchFriendPageService(String email) {
    FriendRequestDTO friendRequestDTO = new FriendRequestDTO();

    // for getting user data
    User user = userRepository.findByEmail(email);
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(user.getUsername());
    userDTO.setName(user.getName());
    userDTO.setEmail(email);
    userDTO.setBackgroundImage(user.getBackgroundImage());
    userDTO.setBio(user.getBio());
    userDTO.setImgurl(user.getImgurl());
    userDTO.setDate(user.getJoin());
    friendRequestDTO.setUser(userDTO);
    friendRequestDTO.setResponse("valid");

    //for getting friend requests
    List<Friend> friendRequests = friendRepository.findFriendRequestLists(userRepository.findIdByEmail(email));
    List<UserDTO> friendRequestsDTOs = new ArrayList<>();
    for (Friend friend : friendRequests) {
      UserDTO friendDTO = new UserDTO();
      User friendUser;
      if (user.getId() == friend.getUserId()) {
        friendUser = userRepository.findByEmail(userRepository.findEmailById(friend.getFriendId()));
      } else {
        friendUser = userRepository.findByEmail(userRepository.findEmailById(friend.getUserId()));
      }
      friendDTO.setUsername(friendUser.getUsername());
      friendDTO.setName(friendUser.getName());
      friendDTO.setEmail(friendUser.getEmail());
      friendDTO.setBackgroundImage(friendUser.getBackgroundImage());
      friendDTO.setBio(friendUser.getBio());
      friendDTO.setImgurl(friendUser.getImgurl());
      friendDTO.setDate(friendUser.getJoin());
      friendRequestsDTOs.add(friendDTO);
    }
    friendRequestDTO.setFriends(friendRequestsDTOs);

    return friendRequestDTO;
  }
}
