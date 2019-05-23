package com.github.abigail830.mybatictest.domain.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private ErrorCode code;
    private String message;

    public BizException(ErrorCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(ErrorCode code) {
        super();
        this.code = code;
    }
}
