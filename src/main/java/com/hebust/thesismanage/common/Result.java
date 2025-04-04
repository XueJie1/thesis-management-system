package com.hebust.thesismanage.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<>();
        r.data = object;
        r.code = 0;
        return r;
    }
    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.message = msg;
        r.code = 1;
        return r;
    }
}
