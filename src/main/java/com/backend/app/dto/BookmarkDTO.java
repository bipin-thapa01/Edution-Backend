package com.backend.app.dto;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import com.backend.app.database.Enum.BookmarkType;

@Component
public class BookmarkDTO {
  private BookmarkType bookmark;
  private Long bookmarkId;
  private Long userId;
  private String username;
  private String profileUrl;
  private String postDesc;
  private String postUrl;
  private UserDTO user;
  private OffsetDateTime createdAt;
  private PostDTO post;
  private boolean update;

  public OffsetDateTime getCreatedAt(){
    return createdAt;
  }
  public void setCreatedAt(OffsetDateTime createdAt){
    this.createdAt = createdAt;
  }

  public BookmarkType getBookmark(){
    return bookmark;
  }
  public void setBookmark(BookmarkType bookmark){
    this.bookmark = bookmark;
  }

  public Long getUserId(){
    return userId;
  }
  public void setUserId(Long userId){
    this.userId = userId;
  }

  public String getUsername(){
    return username;
  }
  public void setUsername(String username){
    this.username = username;
  }

  public String getProfileUrl(){
    return profileUrl;
  }
  public void setProfileUrl(String profileUrl){
    this.profileUrl = profileUrl;
  }

  public String getPostUrl(){
    return postUrl;
  }
  public void setPostUrl(String postUrl){
    this.postUrl = postUrl;
  }

  public String getPostDesc(){
    return postDesc;
  }
  public void setPostDesc(String postDesc){
    this.postDesc = postDesc;
  }

  public Long getBookmarkId(){
    return bookmarkId;
  }
  public void setBookmarkId(Long bookmarkId){
    this.bookmarkId = bookmarkId;
  }

  public UserDTO getUser(){
    return user;
  }
  public void setUser(UserDTO user){
    this.user = user;
  }

  public PostDTO getPost(){
    return post;
  }
  public void setPost(PostDTO post){
    this.post = post;
  }

  public boolean getUpdate(){
    return update;
  }
  public void setUpdate(boolean update){
    this.update = update;
  }
}
