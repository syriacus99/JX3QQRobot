package com.zhouzhou.controller;

import com.zhouzhou.dao.dto.DailyTaskDto;
import com.zhouzhou.dao.form.GetDailyTaskForm;
import com.zhouzhou.exception.ParameterException;
import com.zhouzhou.feignClient.JX3APIClient;
import com.zhouzhou.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class JX3ApiController {

    @Resource
    private JX3APIClient jx3APIClient;

    @PostMapping("/test")
    public Result test(@RequestBody GetDailyTaskForm getDailyTaskForm) throws ParameterException {
        Result<DailyTaskDto> dailyTask = jx3APIClient.getDailyTask(getDailyTaskForm);
        if (dailyTask.getCode().equals(200)){
            return dailyTask;
        }
        else if (dailyTask.getCode().equals(401)){
            throw new ParameterException("日常参数错误");
        }
        return Result.fail(500,"未知错误",null);
    }
}
