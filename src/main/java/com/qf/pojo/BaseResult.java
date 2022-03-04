package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResult {
    private Integer code;

    private String message;

    private Object data;

    private Long total;

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message=message;
    }

    public BaseResult(Integer code ,String message ,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
