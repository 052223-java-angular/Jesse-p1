package com.revature.music.services;

import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.entities.ForumThread;
import com.revature.music.entities.User;
import com.revature.music.repositories.ForumThreadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumService {

    private final ForumThreadRepository forumThreadRepository;
    private final UserService userService;


    public ForumThread createThread(NewThreadRequest req, String id) {

        User existingUser = userService.findUserById(id).get();
        ForumThread newThread = new ForumThread(req.getTitle(), req.getDescription(), existingUser);

        return forumThreadRepository.save(newThread);
    }

    /**
     * How would the user pass the thread they want to comment on
      * @return
     */
    public String getThreadId()
    {
        return null;
    }

    public List<ForumThread> getAllThreads()
    {
        return forumThreadRepository.findAll();
    }
}
