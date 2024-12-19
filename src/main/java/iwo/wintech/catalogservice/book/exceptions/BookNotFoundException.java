package iwo.wintech.catalogservice.book.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(final String isbn) {
        super(String.format("Book with ISBN %s was not found", isbn));
    }
}
