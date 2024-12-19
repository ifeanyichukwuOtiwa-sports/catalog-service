package iwo.wintech.catalogservice.book.service;
import iwo.wintech.catalogservice.book.dto.BookDto;
import iwo.wintech.catalogservice.book.dto.BookSaveRequest;
import iwo.wintech.catalogservice.book.dto.BookUpdateRequest;

public interface BookService {
    Iterable<BookDto> viewBookList();
    BookDto viewBookDetails(String isbn);
    BookDto addBookToCatalog(BookSaveRequest book);
    void removeBookFromCatalog(String isbn);
    BookDto editBookDetails(String isbn, BookUpdateRequest book);
}
