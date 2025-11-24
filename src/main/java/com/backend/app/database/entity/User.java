package com.backend.app.database.entity;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;
import com.backend.app.database.Enum.AccountType;
import jakarta.persistence.*;


@Component
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  @Column(name = "room_code")
  private Long roomCode;
  private String password;
  private String imgurl;
  private String username;
  @Column(name = "account_type")
  @Enumerated(EnumType.STRING)
  private AccountType type;
  private OffsetDateTime join;
  private String bio;
  public Long getId(){
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }

  public String getName(){
    return name;
  }
  public void setName(String name){
    this.name = name;
  }

  public String getBio(){
    return bio;
  }
  public void setBio(String bio){
    this.bio = bio;
  }

  public OffsetDateTime getJoin(){
    return join;
  }

  public void setJoin(OffsetDateTime join){
    this.join = join;
  }

  public String getEmail(){
    return email;
  }
  public void setEmail(String email){
    this.email = email;
  }

  public Long getRoomCode(){
    return roomCode;
  }
  public void setRoomCode(Long roomCode){
    this.roomCode = roomCode;
  }

  public String getPassword(){
    return password;
  }
  public void setPassword(String password){
    this.password = password;
  }

  public String getImgurl(){
    return imgurl;
  }
  public void setImgurl(String imgurl){
    this.imgurl = imgurl;
  }

  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }

  public AccountType getType(){
    return type;
  }
  public void setType(AccountType type){
    this.type = type;
  }
}
