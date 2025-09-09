package com.backend.app.dto;

import java.time.OffsetDateTime;

import com.backend.app.database.Enum.AccountType;

public class UserNotificationDTO {
  private String description;
  private String source;
  private OffsetDateTime date;
  private String imgurl;
  private String requestType;
  private String status;
  private AccountType type;

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

  public String getrequestType(){
    return requestType;
  }
  public void setRequestType(String requestType){
    this.requestType = requestType;
  }

  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  public AccountType getType(){
    return type;
  }
  public void setType(AccountType type){
    this.type = type;
  }
}
