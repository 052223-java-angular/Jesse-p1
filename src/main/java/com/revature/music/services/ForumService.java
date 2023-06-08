package com.revature.music.services;

import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.entities.ForumThread;
import com.revature.music.entities.User;
import com.revature.music.repositories.ForumThreadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ForumService {

    private final ForumThreadRepository forumRepository;
    private final UserService userService;


    public ForumThread createThread(NewThreadRequest req, String id) {

        User exitingUser = userService.findUserById(id).get();
        ForumThread newThread = new ForumThread(req.getTitle(), req.getDescription(), exitingUser);

        return forumRepository.save(newThread);
    }
}
