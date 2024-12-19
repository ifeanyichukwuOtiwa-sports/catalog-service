package iwo.wintech.catalogservice.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BookUpdateRequest(
        @NotBlank(message = "Book title must be defined")
        String title,
        @NotBlank(message = "Book Author must be defined")
        String author,
        @Positive(message = "Book price must be greater than zero")
        BigDecimal price
) {
}
