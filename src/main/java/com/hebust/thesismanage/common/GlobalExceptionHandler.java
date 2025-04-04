package com.hebust.thesismanage.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class, ResponseBody.class})   // 拦截所有加了RestController注解的类
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)   // @ExceptionHandler(SQLIntegrityConstraintViolationException.class)注解用于捕获SQLIntegrityConstraintViolationException异常，当发生该异常时，调用exceptionHandler方法进行处理。
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        // 如果账号已存在，则返回错误信息，Duplicate entry是MyBatis报错的信息
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = "账号"+ split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("Unkown Fxxking Error😅");
    }
    /**
     * 异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)   // @ExceptionHandler(SQLIntegrityConstraintViolationException.class)注解用于捕获SQLIntegrityConstraintViolationException异常，当发生该异常时，调用exceptionHandler方法进行处理。
    public Result<String> exceptionHandler(CustomException ex) {
        String errMsg = ex.getMessage();
        log.error(errMsg);
        return Result.error(errMsg);
    }
}
