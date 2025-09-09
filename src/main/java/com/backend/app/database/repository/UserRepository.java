package com.backend.app.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.app.database.Enum.AccountType;
import com.backend.app.database.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  public User findByEmail(String email);

  public User findByEmailAndPassword(String email, String password);

  public User findByUsernameAndPassword(String username, String password);

  @Query("SELECT u.imgurl FROM User u WHERE u.username = :username")
  public String findImgurlByUsername(String username);

  @Query("SELECT u.email FROM User u WHERE u.id = :id")
  public String findEmailById(Long id);

  @Query("SELECT u.id from User u where u.username = :username")
  public Long findIdByUsername(String username);

  @Query("SELECT u.type from User u WHERE u.username = :username")
  public AccountType findTypeByUsername(String username);

  @Query("select u.username from User u where u.id = :id")
  public String findUsernameById(Long id);
}
