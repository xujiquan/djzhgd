package com.djzhgd.module.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Result
 * web端请求，后台返回的数据
 * @author zhangh
 * @data 2019/11/26
 */
@Data
public class Result<T> implements Serializable {

    /*错误码*/
    private String code;
    /*提示信息*/
    private String msg;
    /*具体的内容*/
    private Object data;

    // service层返回异常，则调用异常处理
    public Result back(Throwable e){
        return error();
    }
    // service层返回对象，则正确
    public Result back(T date){
        return success(date);
    }

    public Result success(){
        Result result = new Result();
        result.setCode(ResultEnum.RESULT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_OK.getMsg());
        return result;
    }
    public Result success(T date){
        Result result = new Result();
        result.setCode(ResultEnum.RESULT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_OK.getMsg());
        result.setData(date);
        return result;
    }

    public Result error(){
        Result result = new Result();
        result.setCode(ResultEnum.RESULT_ERROR.getCode());
        result.setMsg(ResultEnum.RESULT_ERROR.getMsg());
        return result;
    }

}
