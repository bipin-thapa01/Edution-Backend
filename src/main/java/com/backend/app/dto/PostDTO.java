package com.backend.app.dto;

import java.time.OffsetDateTime;

public class PostDTO {
  private OffsetDateTime createdAt;
  private String profileUrl;
  private String by;
  private String description;
  private String imgurl;
  private Long star;
  private Long save;

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

  public String getDescription(){
    return description;
  }
  public void setDescription(String description){
    this.description = description;
  }

  public String getProfileUrl(){
    return profileUrl;
  }
  public void setProfileUrl(String profileUrl){
    this.profileUrl = profileUrl;
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