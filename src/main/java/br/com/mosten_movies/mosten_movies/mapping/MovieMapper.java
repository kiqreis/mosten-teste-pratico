package br.com.mosten_movies.mosten_movies.mapping;

import br.com.mosten_movies.mosten_movies.dto.MovieRequestDto;
import br.com.mosten_movies.mosten_movies.dto.MovieResponseDto;
import br.com.mosten_movies.mosten_movies.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toEntity(MovieRequestDto dto) {
        return Movie.builder()
                .title(dto.title())
                .genre(dto.genre())
                .imageUrl(dto.imageUrl())
                .description(dto.description())
                .likes(0)
                .dislikes(0)
                .build();
    }

    public MovieResponseDto toResponseDto(Movie entity) {
        return new MovieResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getGenre(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getLikes(),
                entity.getDislikes()
        );
    }

}
