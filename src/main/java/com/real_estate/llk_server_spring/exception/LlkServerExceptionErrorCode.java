package com.real_estate.llk_server_spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum LlkServerExceptionErrorCode {
    NOT_FOUNT_AGENT("001_LLK","LLK real estate agent information not found."),
    NOT_FOUNT_REVIEW("002_LLK","LLK real estate review information not found."),
    DONT_REQUEST_DATA("003_LLK","LLK request data not found."),
    FALE_IMG_CONVERT("004_LLK","LLK img file convert failed."),;

    private String errorCode;
    private String errorMessage;
}
