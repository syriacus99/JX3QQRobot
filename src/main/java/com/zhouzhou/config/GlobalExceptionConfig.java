package com.zhouzhou.config;

import com.zhouzhou.dao.form.GroupMessageForm;
import com.zhouzhou.exception.APINoHandleException;
import com.zhouzhou.exception.ParameterException;
import com.zhouzhou.exception.RobotException;
import com.zhouzhou.feignClient.GocqClient;
import com.zhouzhou.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import static javafx.application.Platform.exit;

@RestControllerAdvice
public class GlobalExceptionConfig {

    @Resource
    private GocqClient gocqClient;

    @ExceptionHandler(ParameterException.class)
    public Result handleParameterException(ParameterException e){
        return Result.fail(401,"参数错误",null);
    }

    @ExceptionHandler(APINoHandleException.class)
    public Result handleAPINoHandleException(APINoHandleException e){
        GroupMessageForm groupMessageForm = new GroupMessageForm();
        groupMessageForm.setGroup_id(Long.parseLong(e.getMessage()));
        groupMessageForm.setMessage("未处理Api异常，可能网络异常");
        gocqClient.send_group_msg(groupMessageForm);
        return Result.fail(500,"未处理Api异常，可能网络异常",null);
    }

    @ExceptionHandler(RobotException.class)
    public Result handleCqcntException(RobotException e){
        return Result.fail(e.getCode(),e.getMessage(),null);
    }


    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        System.out.println(e);
        return Result.fail(500,"服务器繁忙",null);
    }

}
