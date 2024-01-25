package com.ld.poetize.utils.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:36
 **/
@Data
@Schema(name = "统一响应类")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 7071227587524525074L;

    private R() {

    }

    @Schema(description = "响应编码")
    private int code;

    @Schema(description = "响应信息")
    private String msg;

    @Schema(description = "响应数据")
    private T data;


    public static <T> R<T> ok() {
        return restResult(null, HttpStatus.OK.value(), null);
    }

    public static <T> R<T> okForMsg(String msg) {
        return restResult(null, HttpStatus.OK.value(), msg);
    }

    public static <T> R<T> okForData(T data) {
        return restResult(data, HttpStatus.OK.value(), null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, HttpStatus.OK.value(), msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }

    public static <T> R<T> failForMsg(String msg) {
        return restResult(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> R<T> failForData(T data) {
        return restResult(data, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> R<T> failForCodeMsg(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
