package iwo.wintech.catalogservice.book.web;

import iwo.wintech.catalogservice.book.exceptions.BookNotFoundException;
import iwo.wintech.catalogservice.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {
                BookController.class
        }
)
class BookControllerUnitTest {
    @MockitoBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        final String isbn = "1233455677";

        willThrow(BookNotFoundException.class)
                .given(bookService).viewBookDetails(isbn);
        mockMvc.perform(get("/books/{isbn}", isbn))
                .andExpect(status().isNotFound());
    }
}