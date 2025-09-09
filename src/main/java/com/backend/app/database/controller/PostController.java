package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.database.service.PostService;
import com.backend.app.dto.PostDTO;
import com.backend.app.dto.ReceivePostDTO;
import com.backend.app.dto.UserDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:3000")
public class PostController {

  @Autowired
  PostService postService;

  @PostMapping("/post")
  public String postMethodName(@RequestBody PostDTO dt) {
      return postService.publishPost(dt);
  }

  @GetMapping("/discover")
  public ReceivePostDTO getMethodName(@RequestHeader("offset") int offset) {
      return postService.discoverPosts(offset);
  }
}
