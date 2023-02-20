package com.zhouzhou.exception;

public class ParameterException extends RobotException{

    static Integer code = 50000;

    public ParameterException(String message) {
        super(code, message);
    }

}
