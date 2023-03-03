package com.zhouzhou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.config.GlobalVariable;
import com.zhouzhou.dao.MessageType.CQTypeImage;
import com.zhouzhou.dao.dto.*;
import com.zhouzhou.dao.form.*;
import com.zhouzhou.exception.APINoHandleException;
import com.zhouzhou.feignClient.GocqClient;
import com.zhouzhou.feignClient.JX3APIClient;
import com.zhouzhou.service.GroupMessageService;
import com.zhouzhou.util.Result;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {

    @Resource
    private GocqClient gocqClient;

    @Resource
    private JX3APIClient jx3APIClient;

    @Resource
    private GlobalVariable globalVariable;

    @Override
    public void handDailyTask(EventInfo eventInfo, String server) throws APINoHandleException {
        System.out.println("handDailyTask");
        GetDailyTaskForm getDailyTaskForm = new GetDailyTaskForm();
        getDailyTaskForm.setServer(server);
        Result<DailyTaskDto> dailyTask = jx3APIClient.getDailyTask(getDailyTaskForm);
        if(dailyTask.getCode().equals(200)){
            String returnMsg = dailyTask.getData().toString();
            GroupMessageForm groupMessageForm = new GroupMessageForm();
            groupMessageForm.setGroup_id(eventInfo.getGroup_id());
            groupMessageForm.setMessage(returnMsg);
            gocqClient.send_group_msg(groupMessageForm);
        }
        else{
            throw new APINoHandleException(500,eventInfo.getGroup_id().toString());
        }
    }

    @Override
    public void handleAppearance(EventInfo eventInfo) throws APINoHandleException {
        System.out.println("handleAppearance");
        String msg = eventInfo.getMessage();
        if(msg.split(" ").length<=1){
            GroupMessageForm groupMessageForm = new GroupMessageForm();
            groupMessageForm.setGroup_id(eventInfo.getGroup_id());
            groupMessageForm.setMessage("请输入正确命令\n" +
                    "物价 [区服]");
        }
        else {
            String name = msg.split(" ")[1];
            GetAppearanceForm getAppearanceForm = new GetAppearanceForm();
            getAppearanceForm.setName(name);
            Result<List<List<AppearanceDto>>> appearancePrice = jx3APIClient.getAppearancePrice(getAppearanceForm);
            if(appearancePrice.getCode().equals(200)){
                List<AppearanceDto> appearanceDtos = appearancePrice.getData().get(0);
                StringBuilder returnMsg = new StringBuilder();
                for(AppearanceDto eachAppearance : appearanceDtos){
                    returnMsg.append(eachAppearance.toString());
                    returnMsg.append("\n");
                }
                GroupMessageForm groupMessageForm = new GroupMessageForm();
                groupMessageForm.setGroup_id(eventInfo.getGroup_id());
                groupMessageForm.setMessage(returnMsg.toString());
                gocqClient.send_group_msg(groupMessageForm);
            }
            else{
                throw new APINoHandleException(500,eventInfo.getGroup_id().toString());
            }
        }

    }

    @Override
    public void handleGoldPrice(EventInfo eventInfo, String server) throws APINoHandleException {
        GetGoldPriceForm getGoldPriceForm = new GetGoldPriceForm();
        getGoldPriceForm.setServer(server);
        //调用jx3Api获取金价数据
        Result<List<GoldPriceDto>> goldPrice = jx3APIClient.getGoldPrice(getGoldPriceForm);
        if(goldPrice.getCode().equals(200)){
            GoldPriceDto goldPriceDto = goldPrice.getData().get(0);
            //消息返回对象
            GroupMessageForm groupMessageForm = new GroupMessageForm();
            groupMessageForm.setGroup_id(eventInfo.getGroup_id());
            groupMessageForm.setMessage(goldPriceDto.toString());
            gocqClient.send_group_msg(groupMessageForm);
        }
        else{
            throw new APINoHandleException(500,eventInfo.getGroup_id().toString());
        }
    }

    @Override
    public void handleMeetingPre(EventInfo eventInfo) throws APINoHandleException {
        //消息返回对象
        GroupMessageForm<String> groupMessageForm = new GroupMessageForm();
        GroupMessageForm<CQMessageForm> groupMessageCQForm = new GroupMessageForm();
        groupMessageForm.setGroup_id(eventInfo.getGroup_id());
        groupMessageCQForm.setGroup_id(eventInfo.getGroup_id());
        String message = eventInfo.getMessage();
        String[] splitMsg = message.split(" ");
        //如果分割数据后长度为1
        if(splitMsg.length<=1){
            groupMessageForm.setMessage("查询奇遇前置格式为\n 前置(奇遇前置) [奇遇名]");
            gocqClient.send_group_msg(groupMessageForm);
        }
        else{
            GetMeetingPreForm getMeetingPreForm = new GetMeetingPreForm();
            getMeetingPreForm.setName(splitMsg[1]);
            Result<MeetingPreDto> meetingPre = jx3APIClient.getMeetingPre(getMeetingPreForm);
            if(meetingPre.getCode()==200){
                groupMessageForm.setMessage(meetingPre.getData().toString());
                gocqClient.send_group_msg(groupMessageForm);
                groupMessageCQForm.setAuto_escape("true");
                CQMessageForm<CQTypeImage> cqMessageForm = new CQMessageForm<>();
                cqMessageForm.setType("image");
                CQTypeImage cqTypeImage = new CQTypeImage();
                String[] split = meetingPre.getData().getUrl().split("/");
                cqTypeImage.setFile(split[split.length-1]);
                cqTypeImage.setUrl(meetingPre.getData().getUrl());
                cqMessageForm.setData(cqTypeImage);
                groupMessageCQForm.setMessage(cqMessageForm);
                gocqClient.send_group_msg(groupMessageCQForm);
            }
            else if(meetingPre.getCode().equals(401)){
                groupMessageForm.setMessage("参数不正确\n查询奇遇前置格式为\n前置(奇遇前置) [奇遇名]");
                gocqClient.send_group_msg(groupMessageForm);
            }
            else {
                throw new APINoHandleException(500,eventInfo.getGroup_id().toString());
            }
        }
    }

    @Override
    public void handleMeetingStrategy(EventInfo eventInfo) throws APINoHandleException {
        //消息返回对象
        GroupMessageForm<String> groupMessageForm = new GroupMessageForm();
        GroupMessageForm<CQMessageForm> groupMessageCQForm = new GroupMessageForm();
        groupMessageForm.setGroup_id(eventInfo.getGroup_id());
        groupMessageCQForm.setGroup_id(eventInfo.getGroup_id());
        String message = eventInfo.getMessage();
        String[] splitMsg = message.split(" ");
        //如果分割数据后长度为1
        if(splitMsg.length<=1){
            groupMessageForm.setMessage("查询奇遇后续格式为\n 后续(奇遇后续) [奇遇名]");
            gocqClient.send_group_msg(groupMessageForm);
        }
        else{
            GetMeetingStrategyForm getMeetingStrategyForm = new GetMeetingStrategyForm();
            getMeetingStrategyForm.setName(splitMsg[1]);
            Result<MeetingStrategyDto> meetingStrategy = jx3APIClient.getMeetingStrategy(getMeetingStrategyForm);
            if(meetingStrategy.getCode()==200){
                groupMessageForm.setMessage(meetingStrategy.getData().toString());
                gocqClient.send_group_msg(groupMessageForm);
                groupMessageCQForm.setAuto_escape("true");
                CQMessageForm<CQTypeImage> cqMessageForm = new CQMessageForm<>();
                cqMessageForm.setType("image");
                CQTypeImage cqTypeImage = new CQTypeImage();
                String[] split = meetingStrategy.getData().getUrl().split("/");
                cqTypeImage.setFile(split[split.length-1]);
                cqTypeImage.setUrl(meetingStrategy.getData().getUrl());
                cqMessageForm.setData(cqTypeImage);
                groupMessageCQForm.setMessage(cqMessageForm);
                gocqClient.send_group_msg(groupMessageCQForm);
            }
            else if(meetingStrategy.getCode().equals(401)){
                groupMessageForm.setMessage("参数不正确\n查询奇遇后续格式为\n前置(奇遇后续) [奇遇名]");
                gocqClient.send_group_msg(groupMessageForm);
            }
            else {
                throw new APINoHandleException(500,eventInfo.getGroup_id().toString());
            }
        }
    }

    @Override
    public void handleAnnounce(EventInfo eventInfo) throws APINoHandleException {
        //消息返回对象
        GroupMessageForm<String> groupMessageForm = new GroupMessageForm();
        groupMessageForm.setGroup_id(eventInfo.getGroup_id());
        Result<List<AnnounceDto>> announce = jx3APIClient.getAnnounce(new GetAnnounceForm());
        if(announce.getCode().equals(200)){
            List<AnnounceDto> data = announce.getData();
            String returnMsg = "";
            for(AnnounceDto eachAnnounce : data){
                returnMsg += eachAnnounce.toString() + "\n";
            }
            groupMessageForm.setMessage(returnMsg);
            gocqClient.send_group_msg(groupMessageForm);
        }
        else{
            throw new APINoHandleException(500,eventInfo.getGroup_id().toString());
        }

    }

    @Override
    public void handleGameServiceStatusCheck(EventInfo eventInfo) throws APINoHandleException {
        String message = eventInfo.getMessage();
        String[] splitMsg = message.split(" ");
        GroupMessageForm<String> groupMessageForm = new GroupMessageForm();
        groupMessageForm.setGroup_id(eventInfo.getGroup_id());
        if(splitMsg.length>1){
            Result<GameServiceStatusDto> gameServiceStatus = jx3APIClient.getGameServiceStatus(new GetGameServiceStatusForm(splitMsg[1]));
            groupMessageForm.setMessage(gameServiceStatus.getData().toString());
            gocqClient.send_group_msg(groupMessageForm);
        }
        else if(splitMsg.length==1){
            Result<GameServiceStatusDto> gameServiceStatus = jx3APIClient.getGameServiceStatus(new GetGameServiceStatusForm(globalVariable.getServer()));
            groupMessageForm.setMessage(gameServiceStatus.getData().toString());
            gocqClient.send_group_msg(groupMessageForm);
        }
        else{
            groupMessageForm.setMessage("请输入正确的区服名");
            gocqClient.send_group_msg(groupMessageForm);
        }
    }
}
