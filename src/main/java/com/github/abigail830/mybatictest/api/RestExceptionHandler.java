package com.github.abigail830.mybatictest.api;

import com.github.abigail830.mybatictest.domain.exception.BizException;
import com.github.abigail830.mybatictest.domain.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice("com.github.abigail830.mybatictest")
@RestController
public class RestExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResponseEntity<?> bizExceptionHandler(BizException e) {
        if (e.getCode().equals(ErrorCode.WISH_NOT_FOUND))
            return new ResponseEntity<>(e.getCode().name(), HttpStatus.NOT_FOUND);

        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
