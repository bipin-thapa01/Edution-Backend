package com.backend.app.dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//dto used for login/signup and accessing homepage
@Component
public class ResponseDTO {
  private String res;
  @Autowired
  private UserDTO userDTO;
  private String status;
  
  public String getResponse(){
    return res;
  }
  public void setResponse(String res){
    this.res = res;
  }

  public String getStatus(){
    return status;
  }
  public void setStatus(String status){
    this.status = status;
  }

  public UserDTO getUser(){
    return userDTO;
  }
  public void setUser(UserDTO userDTO){
    this.userDTO = userDTO;
  }
}
