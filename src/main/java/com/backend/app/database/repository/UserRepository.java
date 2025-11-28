package com.backend.app.database.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.app.database.Enum.AccountType;
import com.backend.app.database.entity.*;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public User findByEmail(String email);

  public User findByEmailAndPassword(String email, String password);

  public User findByUsernameAndPassword(String username, String password);

  @Modifying
  @Transactional
  @Query("UPDATE User u set u.name = :name where u.email = :email and u.password = :password")
  public int updateNameByEmailAndPassword(@Param("email") String email,@Param("password") String password,@Param("name") String name);

  @Modifying
  @Transactional
  @Query("UPDATE User u set u.username = :username where u.email = :email and u.password = :password")
  public int updateUsernameByEmailAndPassword(@Param("email") String email,@Param("password") String password,@Param("username") String username);

  @Modifying
  @Transactional
  @Query("UPDATE User u set u.bio = :bio where u.email = :email and u.password = :password")
  public int updateBioByEmailAndPassword(@Param("email") String email,@Param("password") String password,@Param("bio") String bio);

  @Modifying
  @Transactional
  @Query("UPDATE User u set u.imgurl = :imgurl where u.email = :email and u.password = :password")
  public int updateImgurlByEmailAndPassword(@Param("email") String email,@Param("password") String password,@Param("imgurl") String imgurl);

  @Modifying
  @Transactional
  @Query("UPDATE User u set u.backgroundImage = :backgroundImage where u.email = :email and u.password = :password")
  public int updateBackgroundImageByEmailAndPassword(@Param("email") String email,@Param("password") String password,@Param("backgroundImage") String backgroundImage);

  @Query("SELECT u.imgurl FROM User u WHERE u.username = :username")
  public String findImgurlByUsername(String username);

  @Query("SELECT u.imgurl FROM User u WHERE u.email = :email")
  public String findImgurlByEmail(String email);

  @Query("SELECT u.imgurl FROM User u WHERE u.id = :id")
  public String findImgurlById(Long id);

  @Query("SELECT u.email FROM User u WHERE u.id = :id")
  public String findEmailById(Long id);

  @Query("SELECT u from User u where u.username = :username")
  public User findByUsername(@Param("username") String username);

  @Query("SELECT u.id from User u where u.username = :username")
  public Long findIdByUsername(String username);

  @Query("SELECT u.id from User u where u.email = :email")
  public Long findIdByEmail(String email);

  @Query("SELECT u.type from User u WHERE u.username = :username")
  public AccountType findTypeByUsername(String username);

  @Query("select u.username from User u where u.id = :id")
  public String findUsernameById(Long id);

  @Query("SELECT u.type from User u where u.email = :email")
  public AccountType findTypeByEmail(String email);

  @Query("SELECT u.name from User u where u.email = :email")
  public String findNameByEmail(String email);

  @Query("SELECT u FROM User u where u.username <> 'admin' and u.username <> :username  ORDER BY u.id DESC")
  List<User> findLatestFiveUsers(String username, Pageable pageable);

  @Query("SELECT u FROM User u where LOWER(u.username) LIKE LOWER(CONCAT('%', :key, '%')) OR LOWER(u.name) LIKE LOWER(CONCAT('%', :key, '%'))")
  List<User> findUsersByKey(@Param("key") String key);
}
