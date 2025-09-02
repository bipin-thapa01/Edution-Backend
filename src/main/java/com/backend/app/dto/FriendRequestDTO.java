package com.backend.app.dto;

import java.util.List;
import org.springframework.stereotype.Component;

//dto for getting friend requests
@Component
public class FriendRequestDTO {
  private String res;
  private List<UserDTO> users;

  public String getResponse(){
    return res;
  }
  public void setResponse(String res){
    this.res = res;
  }

  public List<UserDTO> getUsers(){
    return users;
  }
  public void setUsers(List<UserDTO> users){
    this.users = users;
  }
}
