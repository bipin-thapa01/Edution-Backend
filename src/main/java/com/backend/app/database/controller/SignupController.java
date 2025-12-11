package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.*;
import com.backend.app.database.service.UserService;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.AuthenticateDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(
    origins = {"http://localhost:3000", "https://edution-frontend.vercel.app"},
    allowedHeaders = "*",
    allowCredentials = "true"
)

public class SignupController {
  private final UserService userService;
  public SignupController(UserService userService){
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseDTO postMethodName(@RequestBody AuthenticateDTO dt) {
      return userService.signupUser(dt);
  }
}
