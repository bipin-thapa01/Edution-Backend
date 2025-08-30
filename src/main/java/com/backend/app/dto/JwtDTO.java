package com.backend.app.dto;

public class JwtDTO {
  private String token;

  public void setJwt(String token){
    this.token = token;
  }

  public String getJwt(){
    return token;
  }
}
