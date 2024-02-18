package com.kbtg.bootcamp.posttest;
import com.kbtg.bootcamp.posttest.validation.UserIdValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserIdValidatorTests {
    @Test
    @DisplayName("Check userID length equal 10")
    void checkUserIDLength10() {
        UserIdValidator userIdValidator = new UserIdValidator();
        Boolean result = userIdValidator.isValid(1234567890);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Check userID invalid length")
    void checkUserIDInvalidLength() {
        UserIdValidator userIdValidator = new UserIdValidator();
        Boolean result = userIdValidator.isValid(123456789);
        assertThat(result).isEqualTo(false);
    }
}
