package com.devsphere.result;

import lombok.Data;
import java.io.Serializable;

/**
 * 通用返回类
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; // 状态码 1成功 0和其他数字失败

    private String msg; // 返回结果信息

    private T data; // 返回数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }

}
