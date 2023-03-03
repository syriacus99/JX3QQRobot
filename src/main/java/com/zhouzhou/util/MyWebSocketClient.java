package com.zhouzhou.util;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.config.GlobalVariable;
import com.zhouzhou.dao.dto.GameServiceOpenPushDataDto;
import com.zhouzhou.dao.dto.NewsPushDataDto;
import com.zhouzhou.dao.dto.WSSMsgPushDto;
import com.zhouzhou.dao.form.GroupMessageForm;
import com.zhouzhou.feignClient.GocqClient;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Set;

public class MyWebSocketClient extends WebSocketClient {

    @Resource
    private GlobalVariable globalVariable;

    @Resource
    private GocqClient gocqClient;

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("connect to WSS");
    }

    // 接收到数据时
    @Override
    public void onMessage(String s) {
        WSSMsgPushDto wssMsgPushDto = JSONObject.parseObject(s, WSSMsgPushDto.class);
        GroupMessageForm<String> groupMessageForm = new GroupMessageForm<>();
        List<Long> groupIdList = globalVariable.getGroupIdList();
        // action = 2001 代表开关服消息
        if (wssMsgPushDto.getAction().equals(2001)){
            GameServiceOpenPushDataDto data = (GameServiceOpenPushDataDto) wssMsgPushDto.getData();
            groupMessageForm.setMessage(data.toString());
            for(Long eachGroup : groupIdList){
                groupMessageForm.setGroup_id(eachGroup);
                gocqClient.send_group_msg(groupMessageForm);
            }
        }
        // action = 2002 代表新闻消息推送
        else if (wssMsgPushDto.getAction().equals(2002)) {
            NewsPushDataDto data = (NewsPushDataDto)wssMsgPushDto.getData();
            groupMessageForm.setMessage(data.toString());
            for(Long eachGroup : groupIdList){
                groupMessageForm.setGroup_id(eachGroup);
                gocqClient.send_group_msg(groupMessageForm);
            }
        }

    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
