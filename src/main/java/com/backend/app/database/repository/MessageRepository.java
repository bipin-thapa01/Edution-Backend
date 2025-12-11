package com.backend.app.database.repository;

import com.backend.app.database.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("SELECT m.content FROM Message m WHERE (m.by = :by AND m.to = :to) OR (m.by = :to AND m.to = :by) ORDER BY m.createdAt DESC")
  List<String> findContentByByAndTo(@Param("by") String by, @Param("to") String to);
}
