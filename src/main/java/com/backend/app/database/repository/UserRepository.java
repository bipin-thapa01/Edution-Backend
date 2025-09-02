package com.backend.app.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.app.database.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  public User findByEmail(String email);

  public User findByEmailAndPassword(String email, String password);
}
