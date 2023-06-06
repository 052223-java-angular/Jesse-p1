package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/**
 * Entity for  a role
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "roles")
public class Role {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> users;

    /**
     * Constructor to create an instance of a role
     * @param name - the name of the role
     */
    public Role(String name)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.users = new HashSet<>();
    }
}
