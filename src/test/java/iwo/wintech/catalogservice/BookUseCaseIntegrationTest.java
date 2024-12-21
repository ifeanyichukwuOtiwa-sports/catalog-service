package iwo.wintech.catalogservice;

import iwo.wintech.catalogservice.book.dto.BookDto;
import iwo.wintech.catalogservice.book.dto.BookSaveRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.cloud.config.fail-fast=false"
        }
)
class BookUseCaseIntegrationTest {

    @Autowired
    private WebTestClient testRestClient;


    @Test
    void whenPostRequestThenBookCreated() {
        var expectedResponse = new BookDto("Test Title", "Test Author", new BigDecimal("19.90"), "1231231231");
        var request = new BookSaveRequest("1231231231", "Test Title", "Test Author", new BigDecimal("19.90"));

        testRestClient.post()
                .uri("/books")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                    .isCreated()
                .expectBody(BookDto.class)
                    .value(actualBook -> assertThat(actualBook).isEqualTo(expectedResponse));
    }
}
