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
        if(splitMsg[0].equals("日常") && splitMsg.length==1){
            System.out.println(eventInfo);
            groupMessageService.handDailyTask(eventInfo,globalVariable.getServer());
        }
        else if(splitMsg[0].equals("日常") && splitMsg.length>1){
            System.out.println(eventInfo);
            groupMessageService.handDailyTask(eventInfo,splitMsg[1]);
        }
        else if(splitMsg[0].equals("物价")){
            System.out.println(eventInfo);
            groupMessageService.handleAppearance(eventInfo);
        }
        else if(splitMsg[0].equals("金价") && splitMsg.length==1){
            System.out.println(eventInfo);
            groupMessageService.handleGoldPrice(eventInfo,globalVariable.getServer());
        }
        else if(splitMsg[0].equals("金价") && splitMsg.length>1){
            System.out.println(eventInfo);
            groupMessageService.handleGoldPrice(eventInfo,splitMsg[1]);
        }
        else if(splitMsg[0].equals("前置") || splitMsg[0].equals("奇遇前置")){
            System.out.println(eventInfo);
            groupMessageService.handleMeetingPre(eventInfo);
        }
        else if(splitMsg[0].equals("后续") || splitMsg[0].equals("奇遇后续")){
            System.out.println(eventInfo);
            groupMessageService.handleMeetingStrategy(eventInfo);
        }
        else if(splitMsg[0].equals("公告")){
            System.out.println(eventInfo);
            groupMessageService.handleAnnounce(eventInfo);
        }
        else if(splitMsg[0].equals("开服")){
            System.out.println(eventInfo);
            groupMessageService.handleGameServiceStatusCheck(eventInfo);
        }

    }
}
