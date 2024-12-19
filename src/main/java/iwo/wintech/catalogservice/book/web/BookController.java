package iwo.wintech.catalogservice.book.web;

import iwo.wintech.catalogservice.book.dto.BookDto;
import iwo.wintech.catalogservice.book.dto.BookSaveRequest;
import iwo.wintech.catalogservice.book.dto.BookUpdateRequest;
import iwo.wintech.catalogservice.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("books")
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Iterable<BookDto> get() {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public BookDto getByIsbn(@PathVariable final String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto post(@RequestBody @Valid final BookSaveRequest book) {
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIsbn(@PathVariable final String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto put(@PathVariable final String isbn, @RequestBody @Valid final BookUpdateRequest book) {
        return bookService.editBookDetails(isbn, book);
    }
}
