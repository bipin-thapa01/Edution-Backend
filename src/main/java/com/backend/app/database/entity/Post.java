package com.backend.app.database.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "created_at")
  private OffsetDateTime createdAt;
  private Long by;
  private String description;
  private String imgurl;
  private Long star;
  private Long save;

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

  public Long getBy(){
    return by;
  }
  public void setBy(Long by){
    this.by = by;
  }

  public String getDescription(){
    return description;
  }
  public void setDescription(String description){
    this.description = description;
  }

  public String getImgurl(){
    return imgurl;
  }
  public void setImgurl(String imgurl){
    this.imgurl = imgurl;
  }

  public Long getStar(){
    return star;
  }
  public void setStar(Long star){
    this.star = star;
  }

  public Long getSave(){
    return save;
  }
  public void setSave(Long save){
    this.save = save;
  }
}
