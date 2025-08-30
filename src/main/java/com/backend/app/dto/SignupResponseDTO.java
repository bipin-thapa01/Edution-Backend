package com.backend.app.dto;

public class SignupResponseDTO {
  String res;
  public SignupResponseDTO(String res){
    this.res = res;
  }
  public String getResponse(){
    return res;
  }
}
