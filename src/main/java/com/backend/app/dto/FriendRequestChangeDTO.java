package com.backend.app.dto;

public class FriendRequestChangeDTO {
  private String response;
  private String username;
  private String source;

  public String getResponse(){
    return response;
  }
  public void setResponse(String response){
    this.response = response;
  }

  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }

  public String getSource(){
    return source;
  }
  public void setSource(String source){
    this.source = source;
  }
}
