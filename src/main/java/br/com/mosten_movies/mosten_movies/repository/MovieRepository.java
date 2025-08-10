package br.com.mosten_movies.mosten_movies.repository;

import br.com.mosten_movies.mosten_movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT SUM(m.likes) FROM Movie m")
    Optional<Integer> getTotalLikes();

    @Query("SELECT SUM(m.dislikes) FROM Movie m")
    Optional<Integer> getTotalDislikes();

}