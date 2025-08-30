package com.backend.app.database.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.backend.app.JwtUtil;
import com.backend.app.database.entity.User;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.AuthenticateDTO;

@Service
public class UserService {

  @Autowired
  private JwtUtil jwtUtil;

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getById(Long id) {
    return userRepository.findById(id);
  }

  public ResponseDTO signupUser(AuthenticateDTO dt) {
    ResponseDTO responseDTO = new ResponseDTO();
    List<User> users = userRepository.findAll();
    for (User user : users) {
      if (user.getEmail().equals(dt.getEmail())) {
        responseDTO.setResponse("Same Email Error!");
        return responseDTO;
      }
    }
    User userToBeInserted = new User();
    userToBeInserted.setName(dt.getName());
    userToBeInserted.setEmail(dt.getEmail());
    userToBeInserted.setPassword(dt.getPassword());
    userToBeInserted.setRoomCode(dt.getCode());
    userToBeInserted.setImgurl("https://i.postimg.cc/bNsWB5RP/e7df128ed6ca1c2afdedb5d12eaff0be.jpg");

    userRepository.save(userToBeInserted);

    responseDTO.setResponse("Account Created Successfully");
    return responseDTO;
  }

  public ResponseEntity<Map<String,String>> loginUser(AuthenticateDTO dt){
    User u = userRepository.findByEmailAndPassword(dt.getEmail(), dt.getPassword());
    if(u == null){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid email or password!"));
    }
    String token = jwtUtil.generateToken(u.getId().toString(), dt.getEmail());
    return ResponseEntity.ok(Map.of("token",token));
  }
}