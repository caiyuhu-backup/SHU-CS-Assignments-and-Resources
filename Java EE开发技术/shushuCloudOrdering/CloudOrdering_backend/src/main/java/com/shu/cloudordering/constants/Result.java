package com.shu.cloudordering.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result success(String successfulMessage, Object data) {
        return new Result(200, successfulMessage, data);
    }

    public static Result fail(String errorMsg) {
        return new Result(401, errorMsg, null);
    }
}
