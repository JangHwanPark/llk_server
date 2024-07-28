package com.real_estate.llk_server_spring.common;

public record CommonDTO<T>(Boolean aBoolean, T data) {
    public static <T> CommonDTO<T> success(T data) {
        return new CommonDTO<>(true, data);
    }
    public static <T> CommonDTO<T> fail(T data) {
        return new CommonDTO<>(false, data);
    }
}
