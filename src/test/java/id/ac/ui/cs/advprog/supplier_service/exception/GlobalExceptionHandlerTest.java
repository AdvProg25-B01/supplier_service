package id.ac.ui.cs.advprog.supplier_service.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleResponseStatusException_NotFound() {
        ResponseStatusException exception = new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Supplier not found");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleResponseStatusException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(404, responseBody.get("status"));
        assertEquals("404 NOT_FOUND", responseBody.get("error"));
        assertEquals("Supplier not found", responseBody.get("message"));
    }

    @Test
    void handleResponseStatusException_BadRequest() {
        ResponseStatusException exception = new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid input");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleResponseStatusException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(400, responseBody.get("status"));
        assertEquals("400 BAD_REQUEST", responseBody.get("error"));
        assertEquals("Invalid input", responseBody.get("message"));
    }

    @Test
    void handleHttpMessageNotReadable_Default() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException(
                "Invalid JSON", mock(Throwable.class), null);

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleHttpMessageNotReadable(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(400, responseBody.get("status"));
        assertEquals("Bad Request", responseBody.get("error"));
        assertEquals("Invalid request format", responseBody.get("message"));
    }

    @Test
    void handleHttpMessageNotReadable_InvalidUUID() {
        InvalidFormatException cause = mock(InvalidFormatException.class);
        when(cause.getTargetType()).thenReturn((Class) UUID.class);
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException(
                "Invalid UUID", cause, null);

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleHttpMessageNotReadable(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(400, responseBody.get("status"));
        assertEquals("Bad Request", responseBody.get("error"));
        assertEquals("Invalid UUID format. UUIDs must use the standard format: 123e4567-e89b-12d3-a456-426614174000", 
                responseBody.get("message"));
    }

    @Test
    void handleHttpMessageNotReadable_OtherInvalidFormat() {
        InvalidFormatException cause = mock(InvalidFormatException.class);
        when(cause.getTargetType()).thenReturn((Class) String.class); // Not UUID
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException(
                "Invalid format", cause, null);

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleHttpMessageNotReadable(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(400, responseBody.get("status"));
        assertEquals("Bad Request", responseBody.get("error"));
        assertEquals("Invalid request format", responseBody.get("message"));
    }

    @Test
    void handleAllExceptions() {
        Exception exception = new RuntimeException("Unexpected error");

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleAllExceptions(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(500, responseBody.get("status"));
        assertEquals("Internal Server Error", responseBody.get("error"));
        assertEquals("An unexpected error occurred", responseBody.get("message"));
    }
}