package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.Friend;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.FriendRequestDTO;
import com.backend.app.dto.UserDTO;

@Service
public class MessageService {
  private UserRepository userRepository;
  private FriendRepository friendRepository;
  public MessageService(UserRepository userRepository, FriendRepository friendRepository){
    this.userRepository = userRepository;
    this.friendRepository = friendRepository;
  }

  public FriendRequestDTO messageService(String email){
    FriendRequestDTO friendRequestDTO = new FriendRequestDTO();

    //user data
    User user = userRepository.findByEmail(email);
    UserDTO userDTO = new UserDTO();
    userDTO.setBackgroundImage(user.getBackgroundImage());
    userDTO.setBio(user.getBio());
    userDTO.setDate(user.getJoin());
    userDTO.setEmail(user.getEmail());
    userDTO.setImgurl(user.getImgurl());
    userDTO.setName(user.getName());
    userDTO.setUsername(user.getUsername());
    friendRequestDTO.setUser(userDTO);

    //friend data
    List<Friend> friend = friendRepository.findFriendLists(user.getId());
    List<UserDTO> friends = new ArrayList<>();
    for(Friend f : friend){
      User friendUser;
      if(f.getUserId() == user.getId()){
        friendUser = userRepository.findByEmail(userRepository.findEmailById(f.getFriendId()));
      }
      else{
        friendUser = userRepository.findByEmail(userRepository.findEmailById(f.getUserId()));
      }
      UserDTO friendDTO = new UserDTO();
      friendDTO.setBio(friendUser.getBio());
      friendDTO.setDate(friendUser.getJoin());
      friendDTO.setEmail(friendUser.getEmail());
      friendDTO.setImgurl(friendUser.getImgurl());
      friendDTO.setName(friendUser.getName());
      friendDTO.setUsername(friendUser.getUsername());
      friendDTO.setType(friendUser.getType());
      friends.add(friendDTO);
    }
    friendRequestDTO.setFriends(friends);

    friendRequestDTO.setResponse("valid");

    return friendRequestDTO;
  }
}
