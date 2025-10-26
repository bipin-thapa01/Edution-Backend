package com.backend.app.database.service;

import org.springframework.stereotype.Service;

import com.backend.app.database.Enum.BookmarkType;
import com.backend.app.database.entity.Bookmark;
import com.backend.app.database.repository.BookmarkRepository;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.dto.ResponseDTO;

@Service
public class BookmarkService {
  BookmarkRepository bookmarkRepository;
  PostRepository postRepository;
  public BookmarkService(BookmarkRepository bookmarkRepository, PostRepository postRepository){
    this.bookmarkRepository = bookmarkRepository;
    this.postRepository = postRepository;
  }

  public ResponseDTO setBookmark(Long userId, BookmarkType bookmarkType, Long bookmarkId){
    int n = bookmarkRepository.insertBookmark(bookmarkType, userId, bookmarkId);
    ResponseDTO responseDTO = new ResponseDTO();
    if(n == 1){
      if(bookmarkType == BookmarkType.post){
        postRepository.increaseSave(bookmarkId);
      }
      responseDTO.setResponse("success");
      return responseDTO;
    }
    return responseDTO;
  }

  public ResponseDTO removeBookmark(BookmarkType bookmarkType, Long userId, Long bookmarkId){
    int n= bookmarkRepository.removeBookmark(userId, bookmarkId);
    ResponseDTO responseDTO = new ResponseDTO();
    if(n==1){
      if(bookmarkType == BookmarkType.post){
        postRepository.decreaseSave(bookmarkId);
      }
      responseDTO.setResponse("success");
      return responseDTO;
    }
    return responseDTO;
  }

  public ResponseDTO isBookmarked(Long bookmarkId, Long userId){
    ResponseDTO responseDTO = new ResponseDTO();
    Bookmark bookmark = bookmarkRepository.checkBookmark(bookmarkId, userId);
    if(bookmark!=null){
      responseDTO.setResponse("exist");
    }
    else{
      responseDTO.setResponse("doesnot_exist");
    }
    return responseDTO;
  }
}
