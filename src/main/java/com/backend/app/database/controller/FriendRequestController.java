package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.JwtUtil;
import com.backend.app.database.entity.User;
import com.backend.app.database.service.FriendService;
import com.backend.app.dto.FriendRequestChangeDTO;
import com.backend.app.dto.FriendRequestDTO;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.UserDTO;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FriendRequestController {
  @Autowired
  FriendRequestDTO list;
  @Autowired
  JwtUtil jwtUtil;
  @Autowired
  UserDTO friendData;
  FriendService friendService;
  public FriendRequestController(FriendService friendService){
    this.friendService = friendService;
  }

  @GetMapping("/friends")
  public FriendRequestDTO getMethodName(@RequestHeader("email") String email) {
    List<User> friends = friendService.getFriendRequests(email);
    List<OffsetDateTime> date = friendService.getDate(email);
    if(friends.size() > 0){
      List<UserDTO> friendsData = new ArrayList<>();//for all friends data
      int i = 0;
      for(User user: friends){
        friendData.setName(user.getName());
        friendData.setUsername(user.getUsername());
        friendData.setEmail(user.getEmail());
        friendData.setRoomCOde(user.getRoomCode());
        friendData.setImgurl(user.getImgurl());
        friendData.setDate(date.get(i));
        friendsData.add(friendData);
        i++;
      }
      list.setUsers(friendsData);
      list.setResponse("available");
      return list;
    }
    else{
      list.setResponse("not available");
      return list;
    }
  }

  @PostMapping("/send-request")
  public ResponseDTO sendRequestController(@RequestBody FriendRequestChangeDTO dt) {
      return friendService.sendRequestService(dt.getUsername(), dt.getFriendUsername());
  }

  @PostMapping("/update-request")
  public ResponseDTO updateRequestController(@RequestBody FriendRequestChangeDTO dt) {
      return friendService.updateFriendTable(dt.getUsername(), dt.getSource(), dt.getResponse());
  }

  @PostMapping("/unfriend")
  public ResponseDTO unfriendController(@RequestBody FriendRequestChangeDTO friendDTO) {
    return friendService.unfriendService(friendDTO);
  }

  @GetMapping("/fetch-friend-page")
  public FriendRequestDTO fetchFriendPageController(@RequestHeader("authorization") String authorization) {
      if(jwtUtil.validateToken(authorization)){
        String email = jwtUtil.extractEmail(authorization);
        return friendService.fetchFriendPageService(email);
      }
      else{
        FriendRequestDTO friendRequestDTO = new FriendRequestDTO();
        friendRequestDTO.setResponse("invalid");
        return friendRequestDTO;
      }
  }
  
  
}
