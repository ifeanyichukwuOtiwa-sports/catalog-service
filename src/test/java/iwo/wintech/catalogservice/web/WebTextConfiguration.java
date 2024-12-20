package iwo.wintech.catalogservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@TestConfiguration
@Slf4j
class WebTextConfiguration {

    @Bean
    @Lazy
    RestClient restClient(final Environment environment) {
        final int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port")));
        log.info("Creating RestClient for port: {}", port);
        return RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();
    }
}
