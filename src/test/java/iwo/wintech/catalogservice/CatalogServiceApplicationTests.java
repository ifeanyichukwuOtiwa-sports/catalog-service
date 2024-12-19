package iwo.wintech.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CatalogServiceApplicationTests {

    @Autowired
    private CatalogServiceApplication app;

    @Test
    void contextLoads() {
        assertThat(app).isNotNull();
    }

}
