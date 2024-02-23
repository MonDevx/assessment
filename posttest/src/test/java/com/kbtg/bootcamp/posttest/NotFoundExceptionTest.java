package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NotFoundExceptionTest {
    @Test
    public void testNotFoundExceptionConstructor() {
        String message = "Some message";
        NotFoundException exception = new NotFoundException(message);
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
    }
}
