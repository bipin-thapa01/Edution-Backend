package com.backend.app.dto;

import org.springframework.stereotype.Component;

@Component
public class AuthenticateDTO {
  private String name;
  private String email;
  private String password;
  private Long code;

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

  public String getPassword(){
    return password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public Long getCode(){
    return code;
  }

  public void setCode(Long code){
    this.code = code;
  }
}
