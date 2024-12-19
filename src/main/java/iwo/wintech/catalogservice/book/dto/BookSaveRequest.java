package iwo.wintech.catalogservice.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BookSaveRequest(
        @NotBlank(message = "Isbn must be valid")
        @Pattern(
                regexp = "^(\\d{10}|\\d{13})$",
                message = "Isbn format must be valid"
        )
        String isbn,

        @NotBlank(message = "Book title must be defined")
        String title,

        @NotBlank(message = "Book Author must be defined")
        String author,

        @Positive(message = "Book price must be greater than zero")
        BigDecimal price
) {
}
