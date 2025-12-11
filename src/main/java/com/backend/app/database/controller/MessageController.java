package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.JwtUtil;
import com.backend.app.database.service.MessageService;
import com.backend.app.dto.FriendRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
@CrossOrigin(
    origins = {"http://localhost:3000", "https://edution-frontend.vercel.app"},
    allowedHeaders = "*",
    allowCredentials = "true"
)

public class MessageController {
  @Autowired
  JwtUtil jwtUtil;

  private MessageService messageService;
  public MessageController(MessageService messageService){
    this.messageService = messageService;
  }

  @GetMapping("/message-fetch")
  public FriendRequestDTO messageController(@RequestHeader("authentication") String authentication) {
    FriendRequestDTO friendRequestDTO = new FriendRequestDTO();
    if(jwtUtil.validateToken(authentication)){
      String email = jwtUtil.extractEmail(authentication);
      return messageService.messageService(email);
    }
    friendRequestDTO.setResponse("invalid");
    return friendRequestDTO;
  }
  
}
