package com.backend.app.dto;

import java.time.OffsetDateTime;

public class PostDTO {
  private Long id;
  private OffsetDateTime createdAt;
  private Long by;
  private String description;
  private String imgurl;
  private Long agree;
  private Long neutral;
  private Long disagree;
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

  public Long getAgree(){
    return agree;
  }
  public void setAgree(Long agree){
    this.agree = agree;
  }

  public Long getNeutral(){
    return neutral;
  }
  public void setNeutral(Long neutral){
    this.neutral = neutral;
  }

  public Long getDisagree(){
    return disagree;
  }
  public void setDisagree(Long disagree){
    this.disagree = disagree;
  }

  public Long getSave(){
    return save;
  }
  public void setSave(Long save){
    this.save = save;
  }
}