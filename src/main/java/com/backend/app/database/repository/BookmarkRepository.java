package com.backend.app.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.backend.app.database.Enum.BookmarkType;
import com.backend.app.database.entity.Bookmark;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark ,Long>{
  @Modifying
  @Transactional
  @Query("INSERT INTO Bookmark(bookmark, userId, bookmarkId) VALUES(:bookmark, :userId, :bookmarkId)")
  public int insertBookmark(BookmarkType bookmark, Long userId, Long bookmarkId);

  @Modifying
  @Transactional
  @Query("DELETE FROM Bookmark WHERE userId = :userId and bookmarkId = :bookmarkId")
  public int removeBookmark(Long userId, Long bookmarkId);

  @Query(value = "SELECT * FROM bookmark_table where bookmark_id = :bookmarkId and user_id = :userId", nativeQuery = true)
  public Bookmark checkBookmark(Long bookmarkId, Long userId);

  @Query("SELECT id from Bookmark where bookmarkId = :bookmarkId and userId = :userId")
  Long isBookmarked(Long bookmarkId, Long userId);

  @Query("SELECT b FROM Bookmark b WHERE userId = :userId")
  List<Bookmark> fetchBookmarks(Long userId);
}
