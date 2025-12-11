package com.backend.app.database.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="message")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "creataed_at")
  private OffsetDateTime createdAt;
  private String by;
  private String to;
  private String content;

  public OffsetDateTime getCreatedAt(){
    return createdAt;
  }
  public void setCreatedAt(OffsetDateTime createdAt){
    this.createdAt = createdAt;
  }

  public String getBy(){
    return by;
  }
  public void setBy(String by){
    this.by = by;
  }

  public String getTo(){
    return to;
  }
  public void setTo(String to){
    this.to = to;
  }

  public String getContent(){
    return content;
  }
  public void setContent(String content){
    this.content = content;
  }
}
