package br.com.mosten_movies.mosten_movies.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ExceptionResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ExceptionResponseDto(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
