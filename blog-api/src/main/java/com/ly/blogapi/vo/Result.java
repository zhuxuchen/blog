package com.ly.blogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.core.util.JsonUtils;

/**
 * <p>
 *     封装返回结果
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }
    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }

}
