package com.revature.music.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewForumComment {

    private String content;
    private String threadId;// Not sure if this is right how would it get passed into here
    private String token;

}
