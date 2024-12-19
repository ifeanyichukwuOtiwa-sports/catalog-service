package iwo.wintech.catalogservice.book.domain;

import lombok.With;

import java.math.BigDecimal;

public record Book(
        String isbn,
        @With String title,
        @With String author,
        @With BigDecimal price
) {
}
