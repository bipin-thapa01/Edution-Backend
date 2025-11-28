package com.backend.app.dto;

public class SettingsDTO {
  private String email;
  private String updateData;
  private String type;
  private String password;

  public String getEmail(){
    return email;
  }
  public void setEmail(String email){
    this.email = email;
  }

  public String getUpdateData(){
    return updateData;
  }
  public void setUpdateData(String updateData){
    this.updateData = updateData;
  }

  public String getType(){
    return type;
  }
  public void setType(String type){
    this.type = type;
  }

  public String getPassword(){
    return password;
  }
  public void setPassword(String password){
    this.password = password;
  }
}
