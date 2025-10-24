package com.backend.app.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.app.database.service.PostService;
import com.backend.app.dto.PostDTO;
import com.backend.app.dto.ReceivePostDTO;
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
  public String postPosts(@RequestBody PostDTO dt) {
      return postService.publishPost(dt);
  }

  @GetMapping("/discover")
  public ReceivePostDTO getDiscoverPost(@RequestHeader("offset") int offset, @RequestHeader("username") String username) {
      return postService.discoverPosts(offset, username);
  }

  @GetMapping("/following")
  public ReceivePostDTO getFollowingPost(@RequestHeader("offset") int offset, @RequestHeader("username") String username) {
      return postService.followingPosts(offset, username);
  }
  
  @PostMapping("/star")
  public String starPost(@RequestBody Long id) {
      return postService.updateStar(id, true);
  }
  
  @PostMapping("/unstar")
  public String unstarPost(@RequestBody Long id) {
      return postService.updateStar(id, false);
  }
  
}
