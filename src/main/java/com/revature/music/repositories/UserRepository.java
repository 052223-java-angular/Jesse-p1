package com.revature.music.repositories;

import com.revature.music.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Since this is a bean, so it doesn't need to be instantiated. This is not a class this is an interface
 */
@Repository// this indicates that this is a bean, takes in the entity type and the id type
public interface UserRepository extends JpaRepository<User,String> {
    /**
     * Finds a user y username.
     * @param username - the username to search for
     * @return an optional containing the User object if found or an empty Optional
     */
    Optional<User> findByUserName(String username);
}
