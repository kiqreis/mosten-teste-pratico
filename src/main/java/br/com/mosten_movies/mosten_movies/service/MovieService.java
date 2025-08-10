package br.com.mosten_movies.mosten_movies.service;

import br.com.mosten_movies.mosten_movies.dto.MovieRequestDto;
import br.com.mosten_movies.mosten_movies.dto.MovieResponseDto;
import br.com.mosten_movies.mosten_movies.exception.MovieNotFoundException;
import br.com.mosten_movies.mosten_movies.mapping.MovieMapper;
import br.com.mosten_movies.mosten_movies.model.Movie;
import br.com.mosten_movies.mosten_movies.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<MovieResponseDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toResponseDto)
                .toList();
    }

    @Transactional
    public MovieResponseDto addMovie(MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.toEntity(movieRequestDto);
        Movie savedMovie = movieRepository.save(movie);

        return movieMapper.toResponseDto(savedMovie);
    }

    @Transactional
    public MovieResponseDto likeMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        movie.incrementLikes();
        Movie updatedMovie = movieRepository.save(movie);

        return movieMapper.toResponseDto(updatedMovie);
    }

    @Transactional
    public MovieResponseDto dislikeMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        movie.incrementDislikes();
        Movie updatedMovie = movieRepository.save(movie);

        return movieMapper.toResponseDto(updatedMovie);
    }

    public Map<String, Integer> getVoteStatistics() {
        Map<String, Integer> stats = new HashMap<>();

        stats.put("totalLikes", movieRepository.getTotalLikes().orElse(0));
        stats.put("totalDislikes", movieRepository.getTotalDislikes().orElse(0));

        return stats;
    }

}
