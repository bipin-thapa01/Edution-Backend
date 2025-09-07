package com.backend.app.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.dto.PostDTO;

@Service
public class PostService {
  @Autowired
  PostRepository postRepository;
  
  public void publichPost(PostDTO dt){

  }
}
