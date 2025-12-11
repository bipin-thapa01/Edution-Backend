package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.JwtUtil;
import com.backend.app.database.service.FeedService;
import com.backend.app.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
@CrossOrigin(
    origins = {"http://localhost:3000", "https://edution-frontend.vercel.app"},
    allowedHeaders = "*",
    allowCredentials = "true"
)

public class HomePageController {

  @Autowired
  JwtUtil jwtUtil;

  FeedService feedService;
  public HomePageController(FeedService feedService){
    this.feedService = feedService;
  }

  @GetMapping("/home")
  public ResponseDTO getMethodName(@RequestHeader ("authorization") String header) {
    String token = header;
      if(jwtUtil.validateToken(token)){
        String email = jwtUtil.extractEmail(token);
        return feedService.fetchFeedData(email);
      }
      else{
        return feedService.fetchFeedData("invalid");
      }
  }
}
