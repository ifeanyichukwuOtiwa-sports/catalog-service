package iwo.wintech.catalogservice.book.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record BookUpdateRequest(
        @NotBlank String title,
        @NotBlank String author,
        @DecimalMin(value = "0.0", inclusive = false) BigDecimal price
) {
}
