package com.backend.app.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookmarkListDTO {
  String response;
  ResponseDTO res;
  List<BookmarkDTO> bookmarkDTOs;

  public String getResponse(){
    return response;
  }
  public void setResponse(String response){
    this.response = response;
  }

  public ResponseDTO getRes(){
    return res;
  }
  public void setRes(ResponseDTO res){
    this.res = res;
  }

  public List<BookmarkDTO> getBookmarkDTOs(){
    return bookmarkDTOs;
  }
  public void setBookmarkDTOs(List<BookmarkDTO> bookmarkDTOs){
    this.bookmarkDTOs = bookmarkDTOs;
  }
}
