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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "roles")
/**
 * Role entity
 */
public class Role {

    //private static final Logger logger = LogManager.getLogger(Role.class);
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> users;

    public Role(String name)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.users = new HashSet<>();
    }
}
