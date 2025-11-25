package com.backend.app.dto;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

import com.backend.app.database.Enum.AccountType;

@Component
public class UserDTO {
  private String name;
  private String username;
  private String email;
  private Long roomCode;
  private String imgurl;
  private String backgroundImage;
  private OffsetDateTime date;
  private AccountType type;
  private String bio;

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

  public String getUsername(){
    return username;
  }
  
  public void setUsername(String username){
    this.username = username;
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

  public void setRoomCOde(Long roomCode){
    this.roomCode = roomCode;
  }

  public String getImgurl(){
    return imgurl;
  }

  public void setImgurl(String imgurl){
    this.imgurl = imgurl;
  }

  public String getBackgroundImage(){
    return backgroundImage;
  }

  public void setBackgroundImage(String backgroundImage){
    this.backgroundImage = backgroundImage;
  }

  public OffsetDateTime getDate(){
    return date;
  }

  public void setDate(OffsetDateTime date){
    this.date = date;
  }

  public AccountType getType(){
    return type;
  }
  public void setType(AccountType type){
    this.type = type;
  }
}