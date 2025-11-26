package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.backend.app.database.entity.Friend;
import com.backend.app.database.entity.Post;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.BookmarkRepository;
import com.backend.app.database.repository.FriendRepository;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.database.repository.StarRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.PostDTO;
import com.backend.app.dto.ProfileDTO;
import com.backend.app.dto.UserDTO;

@Service
public class ProfileService {

  private UserRepository userRepository;
  private PostRepository postRepository;
  private StarRepository starRepository;
  private BookmarkRepository bookmarkRepository;
  private FriendRepository friendRepository;

  public ProfileService(UserRepository userRepository, PostRepository postRepository, StarRepository starRepository, BookmarkRepository bookmarkRepository, FriendRepository friendRepository){
    this.userRepository = userRepository;
    this.postRepository = postRepository;
    this.starRepository = starRepository;
    this.bookmarkRepository = bookmarkRepository;
    this.friendRepository = friendRepository;
  }

  public ProfileDTO fetchProfileDetails(int offset, String username, String email){
    ProfileDTO profileDTO = new ProfileDTO();

    //original user
    User user = userRepository.findByEmail(email);
    UserDTO userDTO = new UserDTO();
    userDTO.setDate(user.getJoin());
    userDTO.setBio(user.getBio());
    userDTO.setEmail(user.getEmail());
    userDTO.setImgurl(user.getImgurl());
    userDTO.setName(user.getName());
    userDTO.setType(user.getType());
    userDTO.setUsername(user.getUsername());
    profileDTO.setUserDTO(userDTO);

    //search user
    String searchEmail = userRepository.findEmailById(userRepository.findIdByUsername(username));
    User searchUser = userRepository.findByEmail(searchEmail);
    if(searchUser == null){
      profileDTO.setResponse("not found");
      return profileDTO;
    }
    //search user DTO
    UserDTO fetchSearchDTO = new UserDTO();
    fetchSearchDTO.setBackgroundImage(searchUser.getBackgroundImage());
    fetchSearchDTO.setDate(searchUser.getJoin());
    fetchSearchDTO.setBio(searchUser.getBio());
    fetchSearchDTO.setEmail(searchUser.getEmail());
    fetchSearchDTO.setImgurl(searchUser.getImgurl());
    fetchSearchDTO.setName(searchUser.getName());
    fetchSearchDTO.setType(searchUser.getType());
    fetchSearchDTO.setUsername(searchUser.getUsername());
    profileDTO.setFriendDTO(fetchSearchDTO);

    //search user posts
    org.springframework.data.domain.Pageable pageable = PageRequest.of(offset/5,5);
    List<Post> posts = postRepository.findPostByUserId(searchUser.getId(), pageable);
    List<PostDTO> postDTOs =  new ArrayList<>();
    for(Post post: posts){
      PostDTO postDTO = new PostDTO();
      postDTO.setBy(searchUser.getUsername());
      postDTO.setCreatedAt(post.getCreatedAt());
      postDTO.setDescription(post.getDescription());
      postDTO.setImgurl(post.getImgurl());
      if(starRepository.isStarred(post.getId(), userRepository.findIdByEmail(email)) != null){
        postDTO.setIsStarred(true);
      }
      else{
        postDTO.setIsStarred(false);
      }
      if(bookmarkRepository.checkBookmark(post.getId(), userRepository.findIdByEmail(email)) != null){
        postDTO.setIsBookmarked(true);
      }
      else{
        postDTO.setIsBookmarked(false);
      }
      postDTO.setPostId(post.getId());
      postDTO.setProfileUrl(searchUser.getImgurl());
      postDTO.setRepostCount(post.getRepostCount());
      postDTO.setSave(post.getSave());
      postDTO.setStar(post.getStar());
      postDTOs.add(postDTO);
    }
    profileDTO.setPostDTOs(postDTOs);

    //search user friends list
    List<Friend> friends = friendRepository.findFriendLists(searchUser.getId());
    List<UserDTO> friendDTOs = new ArrayList<>();
    for(Friend friend : friends){
      User friendDetail;
      if(friend.getUserId().equals(searchUser.getId())){
        friendDetail = userRepository.findByEmail(userRepository.findEmailById(friend.getFriendId()));
      }
      else{
        friendDetail = userRepository.findByEmail(userRepository.findEmailById(friend.getUserId()));
      }
      UserDTO friendDTO = new UserDTO();
      friendDTO.setBio(friendDetail.getBio());
      friendDTO.setDate(friendDetail.getJoin());
      friendDTO.setEmail(friendDetail.getEmail());
      friendDTO.setImgurl(friendDetail.getImgurl());
      friendDTO.setName(friendDetail.getName());
      friendDTO.setUsername(friendDetail.getUsername());
      friendDTO.setType(friendDetail.getType());
      friendDTOs.add(friendDTO);
    }
    profileDTO.setFriendDTOs(friendDTOs);

    profileDTO.setFriendCount(friendRepository.findFriendCount(searchUser.getId()));

    if(email.equals(searchEmail)){
      profileDTO.setIsOwner(true);
      profileDTO.setIsFriend(false);
    }
    else{
      profileDTO.setIsOwner(false);
      profileDTO.setIsFriend(true);
    }

    profileDTO.setResponse("found");
    return profileDTO;
  }
}
