package com.backend.app.database.entity;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import jakarta.persistence.Id;

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
}
