package com.backend.app.dto;

import java.util.List;

public class ProfileDTO {
  private UserDTO userDTO;
  private List<PostDTO> postDTOs;
  private List<UserDTO> friendDTOs;
  private Long friendsCount;
  private boolean isOwner;
  private boolean isFriend;
  private String response;

  public UserDTO getUserDTO(){
    return userDTO;
  }
  public void setUserDTO(UserDTO userDTO){
    this.userDTO = userDTO;
  }

  public List<PostDTO> getPostDTOs(){
    return postDTOs;
  }
  public void setPostDTOs(List<PostDTO> postDTOs){
    this.postDTOs = postDTOs;
  }

  public List<UserDTO> getFriendDTOs(){
    return friendDTOs;
  }
  public void setFriendDTOs(List<UserDTO> friendDTOs){
    this.friendDTOs = friendDTOs;
  }

  public Long getFriendCount(){
    return friendsCount;
  }
  public void setFriendCount(Long friendsCount){
    this.friendsCount = friendsCount;
  }

  public boolean getIsOwner(){
    return isOwner;
  }
  public void setIsOwner(boolean isOwner){
    this.isOwner = isOwner;
  }

  public boolean getIsFriend(){
    return isFriend;
  }
  public void setIsFriend(boolean isFriend){
    this.isFriend = isFriend;
  }

  public String getResponse(){
    return response;
  }
  public void setResponse(String response){
    this.response = response;
  }
}
