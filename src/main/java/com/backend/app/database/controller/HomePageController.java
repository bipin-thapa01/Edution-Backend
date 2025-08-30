package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.JwtUtil;
import com.backend.app.database.service.FeedService;
import com.backend.app.dto.JwtDTO;
import com.backend.app.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class HomePageController {

  @Autowired
  JwtUtil jwtUtil;

  FeedService feedService;
  public HomePageController(FeedService feedService){
    this.feedService = feedService;
  }

  @GetMapping("/home")
  public ResponseDTO getMethodName(@RequestHeader ("Authorization") String header) {
    String token = header.substring(7);
      if(jwtUtil.validateToken(token)){
        String email = jwtUtil.extractEmail(token);
        return feedService.fetchFeedData(email);
      }
      else{
        return feedService.fetchFeedData("invalid");
      }
  }
}
