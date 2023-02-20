package com.zhouzhou.exception;

public class APINoHandleException extends RobotException{

    static Integer code = 50001;

    public APINoHandleException(Integer code, String message) {
        super(code, message);
    }
}
