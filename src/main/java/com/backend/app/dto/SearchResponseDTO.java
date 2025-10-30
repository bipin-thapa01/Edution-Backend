package com.backend.app.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SearchResponseDTO {
  private String key;
  private String type;
  private UserDTO userDTO;
  private List<UserDTO> userDTOs;
  private List<PostDTO> postDTOs;
  private String res;

  public String getKey(){
    return key;
  }
  public void setKey(String key){
    this.key = key;
  }

  public String getType(){
    return type;
  }
  public void setType(String type){
    this.type = type;
  }

  public UserDTO getUserDTO(){
    return userDTO;
  }
  public void setUserDTO(UserDTO userDTO){
    this.userDTO = userDTO;
  }

  public List<UserDTO> getUserDTOs(){
    return userDTOs;
  }
  public void setUserDTOs(List<UserDTO> userDTOs){
    this.userDTOs = userDTOs;
  }

  public List<PostDTO> getPostDTOs(){
    return postDTOs;
  }
  public void setPostDTO(List<PostDTO> postDTOs){
    this.postDTOs = postDTOs;
  }

  public String getRes(){
    return res;
  }
  public void setRes(String res){
    this.res = res;
  }
}
