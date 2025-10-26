package com.backend.app.database.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.database.service.BookmarkService;
import com.backend.app.dto.BookmarkDTO;
import com.backend.app.dto.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BookmarkController {
  private BookmarkService bookmarkService;
  public BookmarkController(BookmarkService bookmarkService){
    this.bookmarkService = bookmarkService;
  }
  @PostMapping("/bookmark")
  public ResponseDTO updateBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
      if(bookmarkDTO.getUpdate()){
        return bookmarkService.setBookmark(bookmarkDTO.getUserId(), bookmarkDTO.getBookmark(), bookmarkDTO.getBookmarkId());
      }
      else{
        return bookmarkService.removeBookmark(bookmarkDTO.getBookmark(), bookmarkDTO.getUserId(), bookmarkDTO.getBookmarkId());
      }
  }
  
  @GetMapping("/check-bookmark")
  public ResponseDTO checkBookmark(@RequestHeader("userId") Long userId, @RequestHeader("bookmarkId") Long bookmarkId) {
      return bookmarkService.isBookmarked(bookmarkId, userId);
  }
  
}
