package com.zhouzhou.exception;

import lombok.Data;

@Data
public class RobotException extends Exception{
    private Integer code;
    public RobotException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
