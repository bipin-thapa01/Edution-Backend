package com.backend.app.database.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.Friend;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.ResponseDTO;

@Service
public class FriendService {
  FriendRepository friendRepository;
  UserRepository userRepository;
  public FriendService(FriendRepository friendRepository, UserRepository userRepository){
    this.friendRepository = friendRepository;
    this.userRepository = userRepository;
  }

  public List<User> getFriendRequests(String email){
    User user = userRepository.findByEmail(email);
    List<Friend> friends = friendRepository.findByUserId(user.getId());
    List<Long> l = new ArrayList<>();
    for(Friend f: friends){
      l.add(f.getFriendId());
    }
    return userRepository.findAllById(l);
  }

  public List<OffsetDateTime> getDate(String email){
    User user = userRepository.findByEmail(email);
    List<Friend> friends = friendRepository.findByUserId(user.getId());
    List<OffsetDateTime> date = new ArrayList<>();
    for(Friend friend: friends){
      date.add(friend.getDate());
    }
    return date;
  }

  public ResponseDTO updateFriendTable(String username, String friendUsername, String response){
    Long userId = userRepository.findIdByUsername(username);
    Long friendId = userRepository.findIdByUsername(friendUsername);
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    int status = friendRepository.updateStatusAndDate(userId, friendId, response, now);
    ResponseDTO responseDTO =  new ResponseDTO();
    if(status == 1){
      responseDTO.setResponse("success");
    }
    else{
      responseDTO.setResponse("error");
    }
    return responseDTO;
  }
}
