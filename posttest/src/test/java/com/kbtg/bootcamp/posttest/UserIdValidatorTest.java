package com.kbtg.bootcamp.posttest;
import com.kbtg.bootcamp.posttest.validation.UserIdValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserIdValidatorTest {

    private UserIdValidator validator = new UserIdValidator();

    @Test
    void isValid() {
        assertTrue(validator.isValid(1234567890, mockConstraintValidatorContext()));
    }

    @Test
    void isValid_LengthLessThan10() {
        assertFalse(validator.isValid(123456789, mockConstraintValidatorContext()));
    }



    private ConstraintValidatorContext mockConstraintValidatorContext() {
        return null;
    }
}
