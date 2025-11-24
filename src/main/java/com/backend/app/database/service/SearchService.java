package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.app.database.entity.Post;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.BookmarkRepository;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.database.repository.StarRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.PostDTO;
import com.backend.app.dto.SearchResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class SearchService {
  private UserRepository userRepository;
  private PostRepository postRepository;
  private FriendRepository friendRepository;
  private StarRepository starRepository;
  private BookmarkRepository bookmarkRepository;

  public SearchService(UserRepository userRepository, PostRepository postRepository,
      FriendRepository friendRepository,
    StarRepository starRepository, BookmarkRepository bookmarkRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
    this.friendRepository = friendRepository;
    this.starRepository = starRepository;
    this.bookmarkRepository = bookmarkRepository;
  }

  public SearchResponseDTO fetchDefaultUsers(String email) {
    SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
    searchResponseDTO.setRes("valid");
    UserDTO userDTO = new UserDTO();
    User user = userRepository.findByEmail(email);
    userDTO.setName(user.getName());
    userDTO.setUsername(user.getUsername());
    userDTO.setEmail(email);
    userDTO.setImgurl(user.getImgurl());
    userDTO.setType(user.getType());
    searchResponseDTO.setUserDTO(userDTO);
    Pageable topFive = PageRequest.of(0, 5);
    List<User> users = userRepository.findLatestFiveUsers(user.getUsername(), topFive);
    List<UserDTO> userDTOs = new ArrayList<>();
    for (User u : users) {
      UserDTO ud = new UserDTO();
      ud.setName(u.getName());
      ud.setUsername(u.getUsername());
      ud.setEmail(u.getEmail());
      ud.setImgurl(u.getImgurl());
      ud.setType(u.getType());
      userDTOs.add(ud);
    }
    searchResponseDTO.setUserDTOs(userDTOs);
    return searchResponseDTO;
  }

  public SearchResponseDTO fetchSearchData(String key, String username) {
    Long id = userRepository.findIdByUsername(username);
    SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
    List<User> users = userRepository.findUsersByKey(key);
    List<UserDTO> userDTOs = new ArrayList<>();
    for (User user : users) {
      UserDTO userDTO = new UserDTO();
      userDTO.setName(user.getName());
      userDTO.setUsername(user.getUsername());
      userDTO.setEmail(user.getEmail());
      userDTO.setImgurl(user.getImgurl());
      userDTO.setType(user.getType());
      userDTOs.add(userDTO);
    }
    searchResponseDTO.setUserDTOs(userDTOs);
    List<Post> posts = postRepository.findPostsByKey(key);
    List<PostDTO> postDTOs = new ArrayList<>();
    for (Post post : posts) {
      PostDTO postDTO = new PostDTO();
      postDTO.setCreatedAt(post.getCreatedAt());
      postDTO.setBy(userRepository.findUsernameById(post.getBy()));
      postDTO.setDescription(post.getDescription());
      postDTO.setImgurl(post.getImgurl());
      postDTO.setStar(post.getStar());
      postDTO.setSave(post.getSave());
      postDTO.setProfileUrl(userRepository.findImgurlById(post.getBy()));
      postDTO.setUserId(id);
      postDTO.setPostId(post.getId());
      postDTO.setType(userRepository.findTypeByUsername(username));
      if(starRepository.isStarred(post.getId(), id) != null){
        postDTO.setIsStarred(true);
      }
      else{
        postDTO.setIsStarred(false);
      }
      if(bookmarkRepository.checkBookmark(post.getId(), id) != null){
        postDTO.setIsBookmarked(true);
      }
      else{
        postDTO.setIsBookmarked(false);
      }
      postDTOs.add(postDTO);
    }
    searchResponseDTO.setPostDTO(postDTOs);
    if (searchResponseDTO.getUserDTOs() != null || searchResponseDTO.getPostDTOs() != null) {
      searchResponseDTO.setRes("valid");
    }
    return searchResponseDTO;
  }

  public boolean isFriendService(String username, String friend) {
    Long id1 = userRepository.findIdByUsername(username);
    Long id2 = userRepository.findIdByUsername(friend);
    Long f1 = friendRepository.isFriend(id1, id2);
    Long f2 = friendRepository.isFriend(id2, id1);
    if (f1 != null || f2 != null) {
      return true;
    } else {
      return false;
    }
  }
}
