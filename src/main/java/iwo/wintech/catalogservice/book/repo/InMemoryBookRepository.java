package iwo.wintech.catalogservice.book.repo;

import iwo.wintech.catalogservice.book.domain.Book;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
public class InMemoryBookRepository {
    private static final Map<String, Book> bookInMemoryStore = new ConcurrentHashMap<>();
//    @Override
    public Iterable<Book> findAll() {
        return bookInMemoryStore.values();
    }

//    @Override
    public Optional<Book> findByIsbn(final String isbn) {
        return Optional.ofNullable(bookInMemoryStore.get(isbn));
    }

//    @Override
    public boolean existsByIsbn(final String isbn) {
        return Objects.nonNull(bookInMemoryStore.get(isbn));
    }

//    @Override
    public Book save(final Book book) {
        bookInMemoryStore.put(book.isbn(), book);
        return book;
    }

//    @Override
    public void deleteByIsbn(final String isbn) {
        bookInMemoryStore.remove(isbn);
    }
}
