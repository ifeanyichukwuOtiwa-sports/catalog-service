package iwo.wintech.catalogservice.book.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(final String isbn) {
        super(String.format("Book with ISBN %s already exists", isbn));
    }
}
