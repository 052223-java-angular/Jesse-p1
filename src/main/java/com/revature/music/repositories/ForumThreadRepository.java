package com.revature.music.repositories;

import com.revature.music.entities.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumThreadRepository extends JpaRepository<ForumThread, String> {


  List<ForumThread> findAllByUserId(String userId);
}
