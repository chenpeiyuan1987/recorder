package org.yuan.project.recorder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuan.project.recorder.utils.Fault;
import org.yuan.project.recorder.utils.Result;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(Fault.class)
    public Object handleFault(HttpServletRequest req, Fault ex) {
        log.error(ex.getMessage(), ex);
        return Result.failure(ex.getCode(), ex.getMessage());
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Object handleException (HttpServletRequest req, Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.failure(ex.getMessage());
    }

}
