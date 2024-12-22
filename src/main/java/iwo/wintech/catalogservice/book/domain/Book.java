package iwo.wintech.catalogservice.book.domain;

import lombok.With;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.time.Instant;

public record Book(
        @Id
        Long id,

        @Version
        int version,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        String publisher,

        String isbn,
        @With
        String title,
        @With
        String author,
        @With
        BigDecimal price
) {

        public static Book of(final String isbn, final String title, final String author, final BigDecimal price, final String publisher) {
                return new Book(null, 0, null, null, publisher, isbn, title, author, price);
        }
}
