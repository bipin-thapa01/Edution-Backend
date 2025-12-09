package com.backend.app.database.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.app.database.Enum.BookmarkType;
import com.backend.app.database.entity.Bookmark;
import com.backend.app.database.entity.UserNotification;
import com.backend.app.database.repository.BookmarkRepository;
import com.backend.app.database.repository.PostRepository;
import com.backend.app.database.repository.UserNotificationRepository;
import com.backend.app.database.repository.UserRepository;
import com.backend.app.dto.BookmarkDTO;
import com.backend.app.dto.BookmarkListDTO;
import com.backend.app.dto.ResponseDTO;
import com.backend.app.dto.UserDTO;

@Service
public class BookmarkService {
  BookmarkRepository bookmarkRepository;
  PostRepository postRepository;
  UserRepository userRepository;
  UserNotificationRepository userNotificationRepository;

  public BookmarkService(BookmarkRepository bookmarkRepository, PostRepository postRepository,
      UserRepository userRepository, UserNotificationRepository userNotificationRepository) {
    this.bookmarkRepository = bookmarkRepository;
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.userNotificationRepository = userNotificationRepository;
  }

  public BookmarkListDTO fetchBookmarks(String email) {
    List<BookmarkDTO> bookmarkDTOs = new ArrayList<>();
    List<Bookmark> bookmarks = bookmarkRepository.fetchBookmarks(userRepository.findIdByEmail(email));
    for (Bookmark bookmark : bookmarks) {
      BookmarkDTO bookmarkDTO = new BookmarkDTO();
      if (bookmark.getBookmark() == BookmarkType.post) {
        bookmarkDTO.setBookmark(bookmark.getBookmark());
        bookmarkDTO.setBookmarkId(bookmark.getBookmarkId());
        bookmarkDTO.setCreatedAt(bookmark.getCreatedAt());
        bookmarkDTO.setProfileUrl(userRepository.findImgurlById(bookmark.getUserId()));
        bookmarkDTO.setPostDesc(postRepository.findPostDescription(bookmark.getBookmarkId()));
        bookmarkDTO.setUsername(userRepository.findUsernameById(bookmark.getUserId()));
        bookmarkDTO.setPostUrl(postRepository.findPostUrl(bookmark.getBookmarkId()));
        bookmarkDTOs.add(bookmarkDTO);
      }
    }
    BookmarkListDTO list = new BookmarkListDTO();
    list.setResponse("success");
    list.setBookmarkDTOs(bookmarkDTOs);
    ResponseDTO responseDTO = new ResponseDTO();
    responseDTO.setResponse("success");
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(userRepository.findUsernameById(userRepository.findIdByEmail(email)));
    userDTO.setImgurl(userRepository.findImgurlByEmail(email));
    userDTO.setType(userRepository.findTypeByEmail(email));
    userDTO.setName(userRepository.findNameByEmail(email));
    responseDTO.setUser(userDTO);
    list.setRes(responseDTO);
    return list;
  }

  public ResponseDTO setBookmark(Long userId, BookmarkType bookmarkType, Long bookmarkId) {
    int n = bookmarkRepository.insertBookmark(bookmarkType, userId, bookmarkId);
    ResponseDTO responseDTO = new ResponseDTO();

    UserNotification userNotification = new UserNotification();
    userNotification.setDate(OffsetDateTime.now());
    userNotification.setDescription("@" + userRepository.findUsernameById(userId) + " bookmarked your post.");
    userNotification.setSource("admin");
    userNotification.setType("post");
    Long id = postRepository.findByById(bookmarkId);
    userNotification.setUserId(id);
    userNotificationRepository.save(userNotification);

    if (n == 1) {
      postRepository.increaseSave(bookmarkId);
      responseDTO.setResponse("success");
      return responseDTO;
    }
    return responseDTO;
  }

  public ResponseDTO removeBookmark(BookmarkType bookmarkType, Long userId, Long bookmarkId) {
    int n = bookmarkRepository.removeBookmark(userId, bookmarkId);
    ResponseDTO responseDTO = new ResponseDTO();
    if (n == 1) {
      if (bookmarkType == BookmarkType.post) {
        postRepository.decreaseSave(bookmarkId);
      }
      responseDTO.setResponse("success");
      return responseDTO;
    }
    return responseDTO;
  }

  public ResponseDTO isBookmarked(Long bookmarkId, Long userId) {
    ResponseDTO responseDTO = new ResponseDTO();
    Bookmark bookmark = bookmarkRepository.checkBookmark(bookmarkId, userId);
    if (bookmark != null) {
      responseDTO.setResponse("exist");
    } else {
      responseDTO.setResponse("doesnot_exist");
    }
    return responseDTO;
  }
}
