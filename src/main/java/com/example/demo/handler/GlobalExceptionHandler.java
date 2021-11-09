package com.example.demo.handler;

import com.example.demo.entity.ReturnMessage;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 json 请求体调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ReturnMessage jsonParamsException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder ret = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String msg = String.format("%s\n", fieldError.getDefaultMessage());
            ret.append(msg);
        }
        return ReturnMessage.fail(400).setMessage(ret.toString());
    }
}
