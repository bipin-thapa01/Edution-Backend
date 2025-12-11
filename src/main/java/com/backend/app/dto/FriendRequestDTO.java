package com.backend.app.dto;

import java.util.List;
import org.springframework.stereotype.Component;

//dto for getting friend requests
@Component
public class FriendRequestDTO {
  private String res;
  private UserDTO user;
  private List<UserDTO> users;
  private List<UserDTO> friends;
  private List<String> lastMessage;

  public String getResponse(){
    return res;
  }
  public void setResponse(String res){
    this.res = res;
  }

  public List<String> getLastMessage(){
    return lastMessage;
  }
  public void setLastMessage(List<String> lastMessage){
    this.lastMessage = lastMessage;
  }

  public List<UserDTO> getUsers(){
    return users;
  }
  public void setUsers(List<UserDTO> users){
    this.users = users;
  }

  public List<UserDTO> getFriends(){
    return friends;
  }
  public void setFriends(List<UserDTO> friends){
    this.friends = friends;
  }

  public UserDTO getUser(){
    return user;
  }
  public void setUser(UserDTO user){
    this.user = user;
  }
}
