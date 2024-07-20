package com.real_estate.llk_server_spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum LlkServerExceptionErrorCode {
    NOT_FOUND_AGENT("001_LLK","LLK real estate agent information not found."),
    NOT_FOUND_REVIEW("002_LLK","LLK real estate review information not found."),
    DONT_REQUEST_DATA("003_LLK","LLK request data not found."),
    FAIL_IMG_CONVERT("004_LLK","LLK img file convert failed."),
    REQUEST_VALUE_DATABASE_DUPLICATE("005_LLK","LLK request value database duplicate."),
    NOT_FOUND_STATE("006_LLK","LLK state data not found."),;

    private String errorCode;
    private String errorMessage;
}
