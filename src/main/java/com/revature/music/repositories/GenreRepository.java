package com.revature.music.repositories;

import com.revature.music.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,String> {
    Genre findByName(String genreName);
}
