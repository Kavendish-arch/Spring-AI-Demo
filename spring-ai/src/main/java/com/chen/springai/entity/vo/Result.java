package com.chen.springai.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.entity.vo
* @className com.chen.springaidemo.entity.vo.Result

* @author chenyingtao
* @date 2025/4/6 17:32
* @version 1.0
* @description @todo 
*/

@Data
@NoArgsConstructor
public class Result {
    private Integer ok;
    private String msg;

    private Result(Integer ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public static Result ok() {
        return new Result(1, "ok");
    }

    public static Result fail(String msg) {
        return new Result(0, msg);
    }
}