package com.revature.music.repositories;

import com.revature.music.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,String> {
    Artist findByName(String artistName);
}
