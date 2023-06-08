package com.revature.music.repositories;

import com.revature.music.entities.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends JpaRepository <ForumPost,String>{
}
