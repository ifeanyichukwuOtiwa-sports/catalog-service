package iwo.wintech.catalogservice.book.service;

import iwo.wintech.catalogservice.book.domain.Book;
import iwo.wintech.catalogservice.book.dto.BookDto;
import iwo.wintech.catalogservice.book.dto.BookSaveRequest;
import iwo.wintech.catalogservice.book.dto.BookUpdateRequest;
import iwo.wintech.catalogservice.book.exceptions.BookAlreadyExistsException;
import iwo.wintech.catalogservice.book.exceptions.BookNotFoundException;
import iwo.wintech.catalogservice.book.mapper.BookMapper;
import iwo.wintech.catalogservice.book.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;


    @Override
    public Iterable<BookDto> viewBookList() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(BookMapper::fromBook)
                .toList();
    }

    @Override
    public BookDto viewBookDetails(final String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(BookMapper::fromBook)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public BookDto addBookToCatalog(final BookSaveRequest request) {
        if (bookRepository.existsByIsbn(request.isbn())) {
            throw new BookAlreadyExistsException(request.isbn());
        }
        final Book book = BookMapper.toBook(request);
        final Book saved = bookRepository.save(book);
        return BookMapper.fromBook(saved);
    }

    @Override
    public void removeBookFromCatalog(final String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public BookDto editBookDetails(final String isbn, final BookUpdateRequest book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> updateExistingBook(existingBook, book))
                .map(BookMapper::fromBook)
                .orElseGet(() -> saveAsNewBook(isbn, book));
    }

    private Book updateExistingBook(final Book existingBook, final BookUpdateRequest updateRequest) {
        var bookToUpdate = existingBook.withAuthor(updateRequest.title())
                .withAuthor(updateRequest.author())
                .withPrice(updateRequest.price());
        return bookRepository.save(bookToUpdate);
    }

    private BookDto saveAsNewBook(final String isbn, final BookUpdateRequest book) {
        return addBookToCatalog(buildRequest(isbn, book));
    }

    private BookSaveRequest buildRequest(final String isbn, final BookUpdateRequest book) {
        return new BookSaveRequest(
                isbn,
                book.title(),
                book.author(),
                book.price(),
                book.publisher()
        );
    }
}
