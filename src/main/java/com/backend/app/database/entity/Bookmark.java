package com.backend.app.database.entity;

import java.time.OffsetDateTime;

import com.backend.app.database.Enum.BookmarkType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bookmark_table")
public class Bookmark {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="created_at")
  private OffsetDateTime createdAt;
  @Enumerated(EnumType.STRING)
  private BookmarkType bookmark;
  @Column(name="user_id")
  private Long userId;
  @Column(name="bookmark_id")
  private Long bookmarkId;

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

  public Long getBookmarkId(){
    return bookmarkId;
  }
  public void setBookmarkId(Long bookmarkId){
    this.bookmarkId = bookmarkId;
  }
}
