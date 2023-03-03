package com.zhouzhou.service;

import com.zhouzhou.dao.form.EventInfo;
import com.zhouzhou.exception.APINoHandleException;

import javax.annotation.Resource;

@Resource
public interface GroupMessageService {

    // 日常查询处理接口
    public void handDailyTask(EventInfo eventInfo,String server) throws APINoHandleException;

    //外观查询处理接口
    public void handleAppearance(EventInfo eventInfo) throws APINoHandleException;

    //金价查询处理接口
    public void handleGoldPrice(EventInfo eventInfo,String server) throws APINoHandleException;

    //奇遇查询接口
    public void handleMeetingPre(EventInfo eventInfo) throws APINoHandleException;

    //奇遇后续接口
    public void handleMeetingStrategy(EventInfo eventInfo) throws APINoHandleException;

    //维护公告接口
    public void  handleAnnounce(EventInfo eventInfo) throws APINoHandleException;

    // 开服状态查询
    public void handleGameServiceStatusCheck(EventInfo eventInfo) throws APINoHandleException;
}
