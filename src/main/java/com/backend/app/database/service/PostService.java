package com.backend.app.database.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.app.database.entity.Post;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.database.repository.StarRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.PostDTO;
import com.backend.app.dto.ReceivePostDTO;

@Service
public class PostService {
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  StarRepository starRepository;
  
  public String publishPost(PostDTO dt){
    Post post = new Post();
    post.setCreatedAt(OffsetDateTime.now());
    post.setDescription(dt.getDescription());
    post.setBy(userRepository.findIdByUsername(dt.getBy()));
    post.setImgurl(dt.getImgurl());
    post.setStar(Long.valueOf(0));
    post.setSave(Long.valueOf(0));
    post.setRepost(false);
    post.setRepostCount(Long.valueOf(0));
    postRepository.save(post);
    return "posted successfully";
  }

  public ReceivePostDTO discoverPosts(int offset, String username){
      ReceivePostDTO dt = new ReceivePostDTO();
      List<Post> posts = postRepository.getDiscoverPosts(20, offset);
      List<PostDTO> postDTOs = new ArrayList<>();
      for(Post post : posts){
        PostDTO postDto =  new PostDTO();
        Optional<User> userOpt = userRepository.findById(post.getBy());
        User user = userOpt.get();
        postDto.setBy(user.getUsername());
        postDto.setProfileUrl(user.getImgurl());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setDescription(post.getDescription());
        postDto.setImgurl(post.getImgurl());
        Long id = userRepository.findIdByUsername(username);
        postDto.setId(id);
        if(starRepository.isStarred(post.getId(), id) != null){
          postDto.setIsStarred(true);
        }
        else{
          postDto.setIsStarred(false);
        }
        postDto.setSave(post.getStar());
        postDto.setStar(post.getStar());
        postDto.setRepost(post.getRepost());
        postDto.setRepostCount(post.getRepostCount());
        postDto.setType(user.getType());
        postDTOs.add(postDto);
      }
      dt.setPosts(postDTOs);
      dt.setResponse("success");
      return dt;
  }

  public ReceivePostDTO followingPosts(int offset, String username){
    ReceivePostDTO dt = new ReceivePostDTO();
    List<Post> posts = postRepository.getFollowingPosts(20, offset, username);
    List<PostDTO> postDTOs = new ArrayList<>();
    for(Post post : posts){
      PostDTO postDto =  new PostDTO();
        Optional<User> userOpt = userRepository.findById(post.getBy());
        User user = userOpt.get();
        postDto.setBy(user.getUsername());
        postDto.setProfileUrl(user.getImgurl());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setDescription(post.getDescription());
        postDto.setImgurl(post.getImgurl());
        postDto.setSave(post.getStar());
        Long id = userRepository.findIdByUsername(username);
        postDto.setId(id);
        if(starRepository.isStarred(post.getId(), id) != null){
          postDto.setIsStarred(true);
        }
        else{
          postDto.setIsStarred(false);
        }
        postDto.setStar(post.getStar());
        postDto.setRepost(post.getRepost());
        postDto.setRepostCount(post.getRepostCount());
        postDto.setType(user.getType());
        postDTOs.add(postDto);
      }
      dt.setPosts(postDTOs);
      dt.setResponse("success");
      return dt;
  }

  public String updateStar(Long id, boolean increaseStar){
    if(increaseStar){
      starRepository.addStar(id)
      int n = postRepository.increaseStar(id);
      if(n == 1){
        return "success";
      }
      else{
        return "unsuccess";
      }
    }
    else{
      int n = postRepository.decreaseStar(id);
      if(n == 1){
        return "success";
      }
      else{
        return "unsuccess";
      }
    }
  }
}
