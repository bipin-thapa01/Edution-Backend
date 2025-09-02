package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.Friend;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.UserRepository;

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
    List<Friend> friends = friendRepository.findByUserdId(user.getId());
    List<Long> l = new ArrayList<>();
    for(Friend f: friends){
      l.add(f.getFriendId());
    }
    return userRepository.findAllById(l);
  }
}
