package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.exception.ApiErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiErrorResponseTest {
    @Test
    public void testSetterSetsProperly() {
        String expectedMessage = "An error occurred";
        HttpStatus expectedHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ZonedDateTime expectedDateTime = ZonedDateTime.now();

        ApiErrorResponse response = new ApiErrorResponse(expectedMessage, expectedHttpStatus, expectedDateTime);

        Assertions.assertEquals(expectedMessage, response.getMessage());
        Assertions.assertEquals(expectedHttpStatus, response.getHttpStatus());
        Assertions.assertEquals(expectedDateTime, response.getDateTime());
    }
}
