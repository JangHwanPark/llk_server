package com.real_estate.llk_server_spring.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class LlkServerException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public LlkServerException(HttpStatus httpStatus, LlkServerExceptionErrorCode errorStatus) {
        super("Error Code: " + errorStatus.getErrorCode() + ", Message: " + errorStatus.getErrorMessage());
        this.httpStatus = httpStatus;
        this.errorCode = errorStatus.getErrorCode();
        this.errorMessage = errorStatus.getErrorMessage();
        log.error("LLK Server Exception: {}", this);
    }
}
