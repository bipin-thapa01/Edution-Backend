package com.backend.app.dto;

import java.time.OffsetDateTime;
import com.backend.app.database.Enum.AccountType;

public class PostDTO {
  private OffsetDateTime createdAt;
  private String profileUrl;
  private String by;
  private String description;
  private String imgurl;
  private Long star;
  private Long save;
  private boolean repost;
  private AccountType type;

  private Long repostCount;

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

  public boolean getRepost(){
    return repost;
  }
  public void setRepost(boolean repost){
    this.repost = repost;
  }

  public Long getRepostCount(){
    return repostCount;
  }
  public void setRepostCount(Long repostCount){
    this.repostCount = repostCount;
  }

  public AccountType getType(){
    return type;
  }
  public void setType(AccountType type){
    this.type = type;
  }
}