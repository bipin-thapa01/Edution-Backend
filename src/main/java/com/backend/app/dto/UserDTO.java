package com.backend.app.dto;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

@Component
public class UserDTO {
  private String name;
  private String username;
  private String email;
  private Long roomCode;
  private String imgurl;
  private OffsetDateTime date;

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
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

  public OffsetDateTime getDate(){
    return date;
  }

  public void setDate(OffsetDateTime date){
    this.date = date;
  }
}