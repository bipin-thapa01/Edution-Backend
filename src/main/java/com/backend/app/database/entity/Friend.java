package com.backend.app.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "friend_request")
public class Friend {
  @Id
  @GeneratedValue(strategy =GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "friend_id")
  private Long friendId;

  public Long getId(){
    return id;
  }
  public void setId(Long id){
    this.id = id;
  }

  public Long getUserId(){
    return userId;
  }
  public void setUserId(Long userId){
    this.userId = userId;
  }

  public Long getFriendId(){
    return friendId;
  }
  public void setFriendId(Long friendId){
    this.friendId = friendId;
  }
}
