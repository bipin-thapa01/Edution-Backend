package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.database.service.FriendService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api")
public class FriendRequestController {
  FriendService friendService;
  public FriendRequestController(FriendService friendService){
    this.friendService = friendService;
  }

  @GetMapping("/friends")
  public String getMethodName(@RequestHeader("email") String email) {
      return new String();
  }
  
}
