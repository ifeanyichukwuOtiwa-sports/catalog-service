package iwo.wintech.catalogservice.book.web;

import iwo.wintech.catalogservice.book.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
class BookJsonTest {

    @Autowired
    private JacksonTester<BookDto> json;

    @Test
    void testSerialize() throws Exception {
        var book = new BookDto("Title", "Author", new BigDecimal("9.90"), "1234567890", "Manning");
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
                .isEqualTo(book.publisher());
        assertThat(jsonContent)
                .extractingJsonPathNumberValue("@.price")
                .satisfies(extractedNumber -> assertThat(book.price())
                        .isEqualByComparingTo(new BigDecimal(String.valueOf(extractedNumber)))
                );
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90,
                    "publisher": "Manning"
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new BookDto( "Title", "Author", new BigDecimal("9.90"), "1234567890", "Manning"));
    }

}
