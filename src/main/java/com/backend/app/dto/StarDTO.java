package com.backend.app.dto;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

@Component
public class StarDTO {
  private Long id;
  private OffsetDateTime createdAt;
  private Long postId;
  private Long userId;

  public Long getId(){
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }

  public OffsetDateTime getCreatedAt(){
    return createdAt;
  }
  public void setCreatedAt(OffsetDateTime createdAt){
    this.createdAt = createdAt;
  }

  public Long getPostId(){
    return postId;
  }
  public void setPostId(Long postId){
    this.postId = postId;
  }

  public Long getUserId(){
    return userId;
  }
  public void setUserId(Long userId){
    this.userId = userId;
  }
}
