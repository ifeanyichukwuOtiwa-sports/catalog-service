package iwo.wintech.catalogservice.book.repo;

import iwo.wintech.catalogservice.DataTestConfig;
import iwo.wintech.catalogservice.book.domain.Book;
import iwo.wintech.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
@ContextConfiguration(classes = {
        DataConfig.class,
        DataTestConfig.class
})
//@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryJdbcTest {

    @Autowired
    private JdbcAggregateOperations template;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBookByIsbnWhenExisting() {
        var bookIsbn = "1234561237";
        final Book book = Book.of(bookIsbn, "Title", "Author", new BigDecimal("12.90"), "Manning");

        template.insert(book);

        final Optional<Book> byIsbn = bookRepository.findByIsbn(bookIsbn);

        assertThat(byIsbn)
                .isPresent()
                .hasValueSatisfying(val -> assertThat(val.isbn()).isEqualTo(bookIsbn));
    }

}