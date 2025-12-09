package com.backend.app.database.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.backend.app.JwtUtil;
import com.backend.app.database.Enum.AccountType;
import com.backend.app.database.entity.User;
import com.backend.app.database.entity.UserNotification;
import com.backend.app.database.repository.UserNotificationRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.AuthenticateDTO;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final UserNotificationRepository userNotificationRepository;
  private final JwtUtil jwtUtil;

  public UserService(
      UserRepository userRepository,
      UserNotificationRepository userNotificationRepository,
      JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.userNotificationRepository = userNotificationRepository;
    this.jwtUtil = jwtUtil;
  }

  public User getByEmail(String email) {
    return userRepository.findByEmail(email);
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
    if (dt.getEmail().equals("") || dt.getName().equals("") || dt.getPassword().equals("")
        || dt.getUsername().equals("")) {
      responseDTO.setResponse("One of the compulsory field is empty!");
      return responseDTO;
    }
    for (User user : users) {
      if (user.getEmail().equals(dt.getEmail())) {
        responseDTO.setResponse("This email is already in use!");
        return responseDTO;
      }
      if (user.getUsername().equals(dt.getUsername())) {
        responseDTO.setResponse("This Username is already in use!");
        return responseDTO;
      }
    }
    User userToBeInserted = new User();
    userToBeInserted.setName(dt.getName());
    userToBeInserted.setUsername(dt.getUsername());
    userToBeInserted.setEmail(dt.getEmail());
    userToBeInserted.setPassword(dt.getPassword());
    userToBeInserted.setRoomCode(dt.getCode());
    userToBeInserted.setImgurl("https://i.postimg.cc/bNsWB5RP/e7df128ed6ca1c2afdedb5d12eaff0be.jpg");
    userToBeInserted.setBackgroundImage("https://i.postimg.cc/RVVKVXYL/dark-anime-scenery-wot9wg412s7h8yxa.jpg");
    userToBeInserted.setType(AccountType.BASIC);
    userToBeInserted.setJoin(OffsetDateTime.now());
    userRepository.save(userToBeInserted);

    UserNotification userNotification = new UserNotification();
    userNotification.setDate(OffsetDateTime.now());
    userNotification.setDescription("Welcome to EDUTION!");
    userNotification.setSource("admin");
    userNotification.setType("admin");
    userNotification.setUserId(userRepository.findIdByUsername(dt.getUsername()));
    userNotificationRepository.save(userNotification);

    responseDTO.setResponse("Account Created Successfully");
    responseDTO.setStatus("done");
    return responseDTO;
  }

  public ResponseEntity<Map<String, String>> loginUser(AuthenticateDTO dt) {
    User u;
    String tokenEmail = "";
    System.out.println(dt.getEmail());
    if (dt.getEmail() != "" && dt.getEmail() != null) {
      u = userRepository.findByEmailAndPassword(dt.getEmail(), dt.getPassword());
      tokenEmail = dt.getEmail();
    } else {
      u = userRepository.findByUsernameAndPassword(dt.getUsername(), dt.getPassword());
      if (u != null) {
        tokenEmail = u.getEmail();
      }
    }
    if (u == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("error", "Invalid email/username or password!"));
    }
    String token = jwtUtil.generateToken(u.getId().toString(), tokenEmail);
    return ResponseEntity.ok(Map.of("token", token));
  }
}