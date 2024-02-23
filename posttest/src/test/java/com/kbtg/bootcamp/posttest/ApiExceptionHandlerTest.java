package com.kbtg.bootcamp.posttest;
import com.kbtg.bootcamp.posttest.exception.ApiErrorResponse;
import com.kbtg.bootcamp.posttest.exception.ApiExceptionHandler;
import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApiExceptionHandlerTest {

    private final ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();

    @Test
    void handleNotFoundException() {
        // Given
        NotFoundException exception = new NotFoundException("Not found exception message");

        // When
        ResponseEntity<ApiErrorResponse> response = apiExceptionHandler.handleNotFoundException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Not found exception message", response.getBody().getMessage());
        assertNotNull(response.getBody().getDateTime());
    }

    @Test
    void handleBadRequestException() {
        // Given
        BadRequestException exception = new BadRequestException("Bad request exception message");

        // When
        ResponseEntity<ApiErrorResponse> response = apiExceptionHandler.handleBadRequestException(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Bad request exception message", response.getBody().getMessage());
        assertNotNull(response.getBody().getDateTime());
    }

    @Test
    void handleValidationErrors() {
        // Given
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "Validation error message");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        when(exception.getBindingResult()).thenReturn(bindingResult);

        // When
        ResponseEntity<ApiErrorResponse> response = apiExceptionHandler.handleValidationErrors(exception);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("[Validation error message]", response.getBody().getMessage());
        assertNotNull(response.getBody().getDateTime());
    }
}

