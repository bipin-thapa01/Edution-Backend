package com.backend.app.database.entity;

import java.lang.Long;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_notification")
public class UserNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "user_id")
  private Long userId;
  private String description;
  private String source;
  private OffsetDateTime date;

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

  public String getDescription(){
    return description;
  }
  public void setDescription(String description){
    this.description = description;;
  }

  public String getSource(){
    return source;
  }
  public void setSource(String source){
    this.source = source;
  }

  public OffsetDateTime getDate(){
    return date;
  }
  public void setDate(OffsetDateTime date){
    this.date = date;
  }
}
