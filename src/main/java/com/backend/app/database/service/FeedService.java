package com.backend.app.database.service;
import org.springframework.stereotype.Service;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class FeedService {
  private UserRepository userRepository;
  public FeedService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public ResponseDTO fetchFeedData(String email){
    ResponseDTO responseDTO = new ResponseDTO();
    UserDTO userDTO =  new UserDTO();
    if(email.equals("invalid")){
      responseDTO.setResponse("invalid");
      return responseDTO;
    }
    else{
      User user = userRepository.findByEmail(email);
      userDTO.setName(user.getName());
      userDTO.setUsername(user.getUsername());
      userDTO.setEmail(user.getEmail());
      userDTO.setImgurl(user.getImgurl());
      responseDTO.setResponse("valid");
      responseDTO.setUser(userDTO);
      return responseDTO;
    }
  }
}
