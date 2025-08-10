package br.com.mosten_movies.mosten_movies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MovieRequestDto(
        @NotBlank(message = "Title is required")
        @Size(max = 120, message = "Title must be at most 120 characters")
        String title,

        @NotBlank(message = "Genre is required")
        @Size(max = 60, message = "Genre must be at most 60 characters")
        String genre,

        @NotBlank(message = "Image URL is required")
        String imageUrl,

        @Size(max = 255, message = "Description must be at most 255 characters")
        String description
) {
}