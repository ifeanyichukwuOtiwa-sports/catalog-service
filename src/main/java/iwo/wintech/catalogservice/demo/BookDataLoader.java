package iwo.wintech.catalogservice.demo;

import iwo.wintech.catalogservice.book.domain.Book;
import iwo.wintech.catalogservice.book.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Profile("testdata")
@Component
public class BookDataLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        final Book book1 = new Book("1234567891", "Northern Lights", "Lyra Silverstar", new BigDecimal("9.90"));
        final Book book2 = new Book("1234567892", "Polar Journey", "Iorek Polarson", new BigDecimal("12.90"));

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
