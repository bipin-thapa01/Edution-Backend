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
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.StarDTO;

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
        postDto.setUserId(id);
        postDto.setPostId(post.getId());
        if(starRepository.isStarred(post.getId(), id) != null){
          postDto.setIsStarred(true);
        }
        else{
          postDto.setIsStarred(false);
        }
        postDto.setSave(post.getSave());
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
        postDto.setUserId(id);
        postDto.setPostId(post.getId());
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

  public ResponseDTO updateStar(StarDTO starDTO, boolean increaseStar){
    ResponseDTO responseDTO = new ResponseDTO();
    if(increaseStar){
      int n1 = starRepository.addStar(starDTO.getPostId(), starDTO.getUserId());
      int n2 = postRepository.increaseStar(starDTO.getPostId());
      if(n1 == 1 && n2 == 1){
        responseDTO.setResponse("success");
        return responseDTO;
      }
      else{
        responseDTO.setResponse("unsuccess");
        return responseDTO;
      }
    }
    else{
      int n1 = starRepository.removeStar(starDTO.getPostId(), starDTO.getUserId());
      int n2 = postRepository.decreaseStar(starDTO.getPostId());
      if(n1 == 1 && n2 == 1){
        responseDTO.setResponse("success");
        return responseDTO;
      }
      else{
        responseDTO.setResponse("unsuccess");
        return responseDTO;
      }
    }
  }

  public PostDTO getPost(Long postId, Long userId){
    PostDTO postDTO = new PostDTO();
    if(starRepository.isStarred(postId, userId) != null){
      postDTO.setIsStarred(true);
    }
    else{
      postDTO.setIsStarred(false);
    }
    return postDTO;
  }
}
