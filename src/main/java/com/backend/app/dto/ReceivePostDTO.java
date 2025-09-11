package com.backend.app.dto;

import java.util.List;

public class ReceivePostDTO {
  private String response;
  private List<PostDTO> posts;
  
  public List<PostDTO> getPosts(){
    return posts;
  }
  public void setPosts(List<PostDTO> posts){
    this.posts = posts;
  }

  public String getResponse(){
    return response;
  }
  public void setResponse(String response){
    this.response = response;
  }
}
