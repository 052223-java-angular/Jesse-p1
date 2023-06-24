package com.revature.music.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * this is a model for a user request. The difference between an entity and a model is because the dto is how we expect
 * the response or request to look. Once everything is processed we can send a response directory in this case the principal.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUserRequest
{
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String confirmPassword;
}
