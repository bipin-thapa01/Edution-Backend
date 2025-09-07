package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.database.service.PostService;
import com.backend.app.dto.PostDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class PostController {

  @Autowired
  PostService postService;

  @PostMapping("/post")
  public String postMethodName(@RequestBody PostDTO dt) {
      postService.publichPost(dt);
      return "";
  }
  
}
