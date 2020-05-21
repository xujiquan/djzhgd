package com.djzhgd.framework.config;

import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xujiquan
 * @create 2020-05-20 15:55
 */
@RestControllerAdvice
@Slf4j
public class InterfaceExceptionHandler {

    /**
     * 拦截所有运行时的全局异常   
     */
   /* @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        // 返回 JOSN
        Result<Object> result = new Result<>();
        result.setData(e.getMessage());
        result.setCode("error");
        result.setMsg("error");
        return result;
    }*/

    /**
     * 系统异常捕获处理
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        InterfaceExceptionHandler.log.error(e.getMessage(), e);
        // 返回 JOSN
        Result<Object> result = new Result<>();
        result.setData(e.getMessage());
        result.setCode(ResultEnum.RESULT_ERROR.getCode());
        result.setMsg(ResultEnum.RESULT_ERROR.getMsg());
        return result;
    }

}



