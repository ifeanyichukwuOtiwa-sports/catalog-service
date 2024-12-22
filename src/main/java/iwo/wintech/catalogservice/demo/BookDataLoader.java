package iwo.wintech.catalogservice.demo;

import iwo.wintech.catalogservice.book.domain.Book;
import iwo.wintech.catalogservice.book.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Profile("testdata")
@Slf4j
@Component
public class BookDataLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        final Book book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", new BigDecimal("9.90"), "Manning");
        final Book book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", new BigDecimal("12.90"), "O'Reilly");

        log.info("Book data loaded");
        final Iterable<Book> books = bookRepository.saveAll(List.of(book1, book2));
        log.info("Saved: {}", books.toString());
    }
}
