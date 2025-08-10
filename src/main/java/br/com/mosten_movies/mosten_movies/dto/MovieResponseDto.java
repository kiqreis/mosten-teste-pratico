package br.com.mosten_movies.mosten_movies.dto;

public record MovieResponseDto(
        Long id,
        String title,
        String genre,
        String description,
        String imageUrl,
        int likes,
        int dislikes
) {
}
