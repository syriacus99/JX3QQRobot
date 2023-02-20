package com.zhouzhou.listener;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.dao.MessageType.CQTypeImage;
import com.zhouzhou.dao.dto.MeetingPreDto;
import com.zhouzhou.dao.form.CQMessageForm;
import com.zhouzhou.dao.form.EventInfo;
import com.zhouzhou.dao.form.GetMeetingPreForm;
import com.zhouzhou.dao.form.PrivateMessageForm;
import com.zhouzhou.feignClient.GocqClient;
import com.zhouzhou.feignClient.JX3APIClient;
import com.zhouzhou.util.Result;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public  class FriendListener {

    @Resource
    private GocqClient gocqClient;


    @Resource
    private JX3APIClient jx3APIClient;

    public void addListen(EventInfo eventInfo){
//        PrivateMessageForm<CQMessageForm> privateMessageForm = new PrivateMessageForm();
//        privateMessageForm.setUser_id(eventInfo.getUser_id());
//        GetMeetingPreForm getMeetingPreForm = new GetMeetingPreForm();
//        getMeetingPreForm.setName("黑白路");
//        Result<MeetingPreDto> meetingPre = jx3APIClient.getMeetingPre(getMeetingPreForm);
//        if(meetingPre.getCode()==200){
//            privateMessageForm.setAuto_escape("true");
//            CQMessageForm<CQTypeImage> cqMessageForm = new CQMessageForm<>();
//            cqMessageForm.setType("image");
//            CQTypeImage cqTypeImage = new CQTypeImage();
//            String[] split = meetingPre.getData().getUrl().split("/");
//            cqTypeImage.setFile(split[split.length-1]);
//            cqTypeImage.setUrl(meetingPre.getData().getUrl());
//            cqMessageForm.setData(cqTypeImage);
//            privateMessageForm.setMessage(cqMessageForm);
//            gocqClient.send_private_msg(privateMessageForm);
//        }
    }
}
