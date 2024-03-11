package com.vidracariaCampos.unit.config;
import com.vidracariaCampos.ConfigSpringTest;
import com.vidracariaCampos.config.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest extends ConfigSpringTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    public GlobalExceptionHandlerTest(GlobalExceptionHandler globalExceptionHandler) {
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Test
    void handleNoHandlerFoundException() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/example", null);

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleNoHandlerFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("No endpoint GET /example", responseEntity.getBody());
    }

    @Test
    void handleMethodNotSupported() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("POST");

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleMethodNotSupported(ex);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
        assertEquals("Request method 'POST' is not supported for this endpoint", responseEntity.getBody());
    }

    @Test
    void handleRuntimeException() {
        RuntimeException ex = new RuntimeException("Internal server error");

        ResponseEntity<String> responseEntity = globalExceptionHandler.handleRuntimeException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Internal server error", responseEntity.getBody());
    }

}
