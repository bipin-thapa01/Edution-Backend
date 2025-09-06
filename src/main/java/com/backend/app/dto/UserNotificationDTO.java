package com.backend.app.dto;

import java.time.OffsetDateTime;

public class UserNotificationDTO {
  private String description;
  private String source;
  private OffsetDateTime date;
  private String imgurl;
  private String type;
  private String status;

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

  public String getImgurl(){
    return imgurl;
  }
  public void setImgurl(String imgurl){
    this.imgurl = imgurl;
  }

  public String getType(){
    return type;
  }
  public void setType(String type){
    this.type = type;
  }

  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
}
