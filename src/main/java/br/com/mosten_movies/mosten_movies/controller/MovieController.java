package br.com.mosten_movies.mosten_movies.controller;

import br.com.mosten_movies.mosten_movies.dto.MovieRequestDto;
import br.com.mosten_movies.mosten_movies.dto.MovieResponseDto;
import br.com.mosten_movies.mosten_movies.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> addMovie(@Valid @RequestBody MovieRequestDto movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.addMovie(movie));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<MovieResponseDto> likeMovie(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.likeMovie(id));
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity<MovieResponseDto> dislikeMovie(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.dislikeMovie(id));
    }

    @GetMapping("/totals")
    public ResponseEntity<Map<String, Integer>> getVoteStatistics() {
        return ResponseEntity.ok(movieService.getVoteStatistics());
    }

}
