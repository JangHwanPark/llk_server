package com.real_estate.llk_server_spring.user.dto;

public interface Oauth2Response {
    // 제공자(google)
    String getProvider();
    // 제공자에서 발급해 주는 id 값
    String getProviderId();
    // 이메일
    String getEmail();
    // 사용자 실명
    String getName();
}
