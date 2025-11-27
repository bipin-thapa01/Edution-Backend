package com.backend.app.database.service;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.User;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class SettingsService {
  private UserRepository userRepository;
  public SettingsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public ResponseDTO settingsService(String email){
    ResponseDTO responseDTO = new ResponseDTO();
    User user = userRepository.findByEmail(email);
    if(user!=null){
      UserDTO userDTO = new UserDTO();
      userDTO.setBackgroundImage(user.getBackgroundImage());
      userDTO.setBio(user.getBio());
      userDTO.setDate(user.getJoin());
      userDTO.setEmail(user.getEmail());
      userDTO.setImgurl(user.getImgurl());
      userDTO.setName(user.getName());
      userDTO.setUsername(user.getUsername());
      responseDTO.setUser(userDTO);
      responseDTO.setResponse("valid");
      return responseDTO;
    }
    else{
      responseDTO.setResponse("Error fetching user data!");
      return responseDTO;
    }
  }
}
