package iwo.wintech.catalogservice.web;

import iwo.wintech.catalogservice.CatalogServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {
        CatalogServiceApplication.class,
        WebTextConfiguration.class
})
class HomeControllerTest {
    @Autowired
    RestClient client;

    @Test
    void getGreeting() {
        final String response = client.get()
                .uri("/")
                .retrieve()
                .body(String.class);

        assertThat(response).contains("|Welcome To Book Catalog");
    }
}