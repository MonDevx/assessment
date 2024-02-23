package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BadRequestExceptionTest {
    @Test
    public void testBadRequestExceptionConstructor() {
        String message = "Some message";
        BadRequestException exception = new BadRequestException(message);
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
