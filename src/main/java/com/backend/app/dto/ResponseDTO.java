package com.backend.app.dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseDTO {
  private String res;
  @Autowired
  private UserDTO userDTO;
  
  public String getResponse(){
    return res;
  }
  public void setResponse(String res){
    this.res = res;
  }

  public UserDTO getUser(){
    return userDTO;
  }
  public void setUser(UserDTO userDTO){
    this.userDTO = userDTO;
  }
}
