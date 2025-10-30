package com.backend.app.database.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.app.database.entity.User;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.SearchResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class SearchService {
  private UserRepository userRepository;
  private PostRepository postRepository;
  public SearchService(UserRepository userRepository, PostRepository postRepository){
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  public SearchResponseDTO fetchDefaultUsers(String email){
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
    Pageable topFive = PageRequest.of(0,5);
    List<User> users = userRepository.findLatestFiveUsers(topFive);
    List<UserDTO> userDTOs = new ArrayList<>();
    for(User u : users){
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
}
