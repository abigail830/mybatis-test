package com.github.abigail830.mybatictest.api;

import com.github.abigail830.mybatictest.domain.exception.BizException;
import com.github.abigail830.mybatictest.domain.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("com.github.abigail830.mybatictest")
public class RestExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public void jsonErrorHandler(HttpServletRequest req, BizException e) throws Exception {

        if (e.getCode().equals(ErrorCode.WISH_NOT_FOUND))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCode().name());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCode().name());
    }

}
