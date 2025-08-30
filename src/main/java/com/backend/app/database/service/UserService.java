package com.backend.app.database.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.backend.app.database.entity.User;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.SignupResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class UserService {
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

  public SignupResponseDTO signupUser(UserDTO dt) {
    List<User> users = userRepository.findAll();
    for (User user : users) {
      if (user.getEmail().equals(dt.getEmail())) {
        return new SignupResponseDTO("Same Email Error!");
      }
    }
    User userToBeInserted = new User();
    userToBeInserted.setName(dt.getName());
    userToBeInserted.setEmail(dt.getEmail());
    userToBeInserted.setPassword(dt.getPassword());
    userToBeInserted.setRoomCode(dt.getCode());

    userRepository.save(userToBeInserted);

    return new SignupResponseDTO("Account Created Successfully");
  }
}
