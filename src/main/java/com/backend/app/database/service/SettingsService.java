package com.backend.app.database.service;

import org.springframework.stereotype.Service;

import com.backend.app.database.entity.User;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.SettingsDTO;
import com.backend.app.dto.UserDTO;

@Service
public class SettingsService {
  private UserRepository userRepository;

  public SettingsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseDTO settingsService(String email) {
    ResponseDTO responseDTO = new ResponseDTO();
    User user = userRepository.findByEmail(email);
    if (user != null) {
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
    } else {
      responseDTO.setResponse("Error fetching user data!");
      return responseDTO;
    }
  }

  public ResponseDTO settingsUpdateService(SettingsDTO settingsDTO) {
    ResponseDTO responseDTO = new ResponseDTO();
    int n;
    if (settingsDTO.getType().equals("name")) {
      n = userRepository.updateNameByEmailAndPassword(settingsDTO.getEmail(), settingsDTO.getPassword(),
          settingsDTO.getUpdateData());
    }
    else if(settingsDTO.getType().equals("username")){
      n = userRepository.updateUsernameByEmailAndPassword(settingsDTO.getEmail(), settingsDTO.getPassword(), settingsDTO.getUpdateData());
    }
    else if(settingsDTO.getType().equals("bio")){
      n = userRepository.updateBioByEmailAndPassword(settingsDTO.getEmail(), settingsDTO.getPassword(), settingsDTO.getUpdateData());
    }
    else if(settingsDTO.getType().equals("pfp")){
      n = userRepository.updateImgurlByEmailAndPassword(settingsDTO.getEmail(), settingsDTO.getPassword(), settingsDTO.getUpdateData());
    }
    else{
      n = userRepository.updateBackgroundImageByEmailAndPassword(settingsDTO.getEmail(), settingsDTO.getPassword(), settingsDTO.getUpdateData());
    }
    if (n == 1) {
      responseDTO.setResponse("valid");
    }
    else{
      responseDTO.setResponse("invalid");
    }
    return responseDTO;
  }
}
