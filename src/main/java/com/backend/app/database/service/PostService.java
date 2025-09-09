package com.backend.app.database.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.app.database.entity.Post;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.PostDTO;
import com.backend.app.dto.ReceivePostDTO;

@Service
public class PostService {
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;
  
  public String publishPost(PostDTO dt){
    Post post = new Post();
    post.setCreatedAt(OffsetDateTime.now());
    post.setDescription(dt.getDescription());
    post.setBy(userRepository.findIdByUsername(dt.getBy()));
    post.setImgurl(dt.getImgurl());
    post.setStar(Long.valueOf(0));
    post.setSave(Long.valueOf(0));
    postRepository.save(post);
    return "posted successfully";
  }

  public ReceivePostDTO discoverPosts(int offset){
      ReceivePostDTO dt = new ReceivePostDTO();
      List<Post> posts = postRepository.getDiscoverPosts(20, offset);
      List<PostDTO> postDTOs = new ArrayList<>();
      for(Post post : posts){
        PostDTO postDto =  new PostDTO();
        postDto.setBy(userRepository.findUsernameById(post.getBy()));
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setDescription(post.getDescription());
        postDto.setImgurl(post.getImgurl());
        postDto.setSave(post.getStar());
        postDto.setStar(post.getStar());
        postDTOs.add(postDto);
      }
      dt.setPosts(postDTOs);
      dt.getResponse("success");
      return dt;
  }
}
