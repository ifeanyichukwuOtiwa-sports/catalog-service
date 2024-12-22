package iwo.wintech.catalogservice.book.mapper;

import iwo.wintech.catalogservice.book.domain.Book;
import iwo.wintech.catalogservice.book.dto.BookDto;
import iwo.wintech.catalogservice.book.dto.BookSaveRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookMapper {

    public static BookDto fromBook(Book book) {
        return new BookDto(book.title() , book.author(), book.price(), book.isbn(), book.publisher());
    }

    public static Book toBook(final BookSaveRequest req) {
        return Book.of(
                req.isbn(),
                req.title(),
                req.author(),
                req.price(),
                req.publisher()
        );
    }
}
