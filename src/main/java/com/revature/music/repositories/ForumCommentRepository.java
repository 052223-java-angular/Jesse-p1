package com.revature.music.repositories;

import com.revature.music.entities.ForumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumCommentRepository extends JpaRepository <ForumComment,String>{

    //savePostByThreadAndUserId()
}
