package com.backend.app.database.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.app.database.service.UserService;
import com.backend.app.dto.AuthenticateDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class LoginController {
  UserService userService;
  public LoginController(UserService userService){
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<Map<String,String>> postMethodName(@RequestBody AuthenticateDTO dt){
      //TODO: process POST request
      return userService.loginUser(dt);
  }
  
}
