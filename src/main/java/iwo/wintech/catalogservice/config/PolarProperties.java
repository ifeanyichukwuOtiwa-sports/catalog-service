package iwo.wintech.catalogservice.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    /**
     * Message to welcome users
     */
    private String greeting;

    public PolarProperties(String greeting) {
        this.greeting = greeting;
    }

}
