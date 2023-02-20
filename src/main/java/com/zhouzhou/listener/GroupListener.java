package com.zhouzhou.listener;

import com.zhouzhou.config.GlobalVariable;
import com.zhouzhou.dao.dto.DailyTaskDto;
import com.zhouzhou.dao.form.EventInfo;
import com.zhouzhou.dao.form.GetDailyTaskForm;
import com.zhouzhou.dao.form.GroupMessageForm;
import com.zhouzhou.dao.form.PrivateMessageForm;
import com.zhouzhou.exception.APINoHandleException;
import com.zhouzhou.feignClient.GocqClient;
import com.zhouzhou.feignClient.JX3APIClient;
import com.zhouzhou.service.GroupMessageService;
import com.zhouzhou.util.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GroupListener {

    @Resource
    private GlobalVariable globalVariable;

    @Resource
    private GroupMessageService groupMessageService;


    public void addListen(EventInfo eventInfo) throws APINoHandleException {
        String message = eventInfo.getMessage();
        String[] splitMsg = message.split(" ");
        System.out.println(splitMsg);
        if(splitMsg[0].equals("日常") && splitMsg.length==1){
            groupMessageService.handDailyTask(eventInfo,globalVariable.getServer());
        }
        else if(splitMsg[0].equals("日常") && splitMsg.length>1){
            groupMessageService.handDailyTask(eventInfo,splitMsg[1]);
        }
        else if(splitMsg[0].equals("物价")){
            groupMessageService.handleAppearance(eventInfo);
        }
        else if(splitMsg[0].equals("金价") && splitMsg.length==1){
            groupMessageService.handleGoldPrice(eventInfo,globalVariable.getServer());
        }
        else if(splitMsg[0].equals("金价") && splitMsg.length>1){
            groupMessageService.handleGoldPrice(eventInfo,splitMsg[1]);
        }
        else if(splitMsg[0].equals("前置") || splitMsg[0].equals("奇遇前置")){
            groupMessageService.handleMeetingPre(eventInfo);
        }

    }
}
