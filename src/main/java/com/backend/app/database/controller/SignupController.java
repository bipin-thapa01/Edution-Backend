package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.*;
import com.backend.app.database.service.UserService;
import com.backend.app.dto.SignupResponseDTO;
import com.backend.app.dto.UserDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SignupController {
  private final UserService userService;
  public SignupController(UserService userService){
    this.userService = userService;
  }

  @PostMapping("/signup")
  public SignupResponseDTO postMethodName(@RequestBody UserDTO dt) {
      //TODO: process POST request
      return userService.signupUser(dt);
  }
}
