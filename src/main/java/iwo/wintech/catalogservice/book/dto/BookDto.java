package iwo.wintech.catalogservice.book.dto;

import java.math.BigDecimal;

public record BookDto(
        String title,
        String author,
        BigDecimal price,
        String isbn,
        String publisher
) {
}
