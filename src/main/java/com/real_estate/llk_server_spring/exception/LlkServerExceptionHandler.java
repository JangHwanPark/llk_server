package com.real_estate.llk_server_spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class LlkServerExceptionHandler {
    @ExceptionHandler(LlkServerException.class)
    public ResponseEntity<Object> handleLlkServerException(LlkServerException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("Error_Code", e.getErrorCode());
        response.put("Error_Message", e.getMessage());
        return new ResponseEntity<>(response,e.getHttpStatus());
    }
}
