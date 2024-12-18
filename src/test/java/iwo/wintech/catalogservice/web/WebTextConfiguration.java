package iwo.wintech.catalogservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestClient;

@TestConfiguration
@Slf4j
class WebTextConfiguration {

    @Bean
    @Lazy
    RestClient restClient(@Value("${local.server.port}") final int port) {
        log.info("Creating RestClient for port: {}", port);
        return RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();
    }
}
