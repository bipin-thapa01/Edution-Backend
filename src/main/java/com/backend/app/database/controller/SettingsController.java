package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.JwtUtil;
import com.backend.app.database.service.SettingsService;
import com.backend.app.dto.ResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api")
public class SettingsController {

  @Autowired
  private JwtUtil jwtUtil;

  private SettingsService settingsService;
  public SettingsController(SettingsService settingsService){
    this.settingsService = settingsService;
  }

  @GetMapping("/settings")
  public ResponseDTO settingsController(@RequestHeader("authentication") String authentication) {
    if(jwtUtil.validateToken(authentication)){
      String email = jwtUtil.extractEmail(authentication);
      return settingsService.settingsService(email);
    }
    else{
      ResponseDTO responseDTO = new ResponseDTO();
      responseDTO.setResponse("invalid");
      return responseDTO;
    }
  }

}
