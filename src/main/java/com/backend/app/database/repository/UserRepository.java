package com.backend.app.database.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.app.database.entity.*;

public interface UserRepository extends JpaRepository<User, Long>{
  public User findByEmail(String email);
}
