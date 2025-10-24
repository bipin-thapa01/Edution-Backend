package com.backend.app.database.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Star {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "created_at")
  private OffsetDateTime createdAt;
  @Column(name = "post_id")
  private Long postId;
  @Column(name = "user_id")
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
