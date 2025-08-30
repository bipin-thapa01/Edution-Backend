package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
  @PostMapping("path")
  public String postMethodName(@RequestBody String entity) {
      //TODO: process POST request
      
      return entity;
  }
  
}
