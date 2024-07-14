package com.real_estate.llk_server_spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public enum LlkServerExceptionErrorCode {
    NOT_FOUNT_AGENT("001_LLK","Real estate agent information not found.");

    private String errorCode;
    private String errorMessage;
}
