package com.backend.app.dto;

import java.util.List;

public class UserNotificationResponseDTO {
  private String response;
  private UserDTO user;
  private List<UserNotificationDTO> notifications;

  public String getResponse(){
    return response;
  }
  public void setResponse(String response){
    this.response = response;
  }

  public UserDTO getUserDTO(){
    return user;
  }
  public void setUserDTO(UserDTO user){
    this.user = user;
  }

  public List<UserNotificationDTO> getNotifications(){
    return notifications;
  }
  public void setNotifications(List<UserNotificationDTO> notifications){
    this.notifications = notifications;
  }
}
