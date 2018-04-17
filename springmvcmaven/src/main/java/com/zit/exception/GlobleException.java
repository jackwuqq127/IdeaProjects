package com.zit.exception;

import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class GlobleException {
    @ExceptionHandler({ArithmeticException.class,IllegalStateException.class })
    public ModelAndView handlerException2(Exception e){
        ModelAndView modelAndView = new ModelAndView("Error");
        modelAndView.addObject("mesg", "全局异常处理！"+e.getMessage());
        return modelAndView;
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND,reason = "参数中没有找到用户名！")
    public static class NoUserName extends RuntimeException{}
}
