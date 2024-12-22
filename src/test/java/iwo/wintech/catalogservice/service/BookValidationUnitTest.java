package iwo.wintech.catalogservice.service;

import iwo.wintech.catalogservice.book.dto.BookSaveRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BookValidationUnitTest {
    private static Validator validator;


    @BeforeAll
    static void setupValidator() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();){
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsAreCorrectValidationSucceeds() {
        final BookSaveRequest book = new BookSaveRequest("1234567891", "Sample Title", "Sample Author", new BigDecimal("19.9"), "Manniong");
        final Set<ConstraintViolation<BookSaveRequest>> validate = validator.validate(book);
        assertThat(validate).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        final BookSaveRequest book = new BookSaveRequest("123456789", "Sample Title", "Sample Author", new BigDecimal("19.9"), "Manniong");
        final Set<ConstraintViolation<BookSaveRequest>> validate = validator.validate(book);
        assertThat(validate).hasSize(1);
        assertThat(validate.iterator().next().getMessage()).contains("Isbn format must be valid");
    }
}
