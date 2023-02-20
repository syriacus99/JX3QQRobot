package com.zhouzhou.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.dao.form.EventInfo;
import com.zhouzhou.exception.APINoHandleException;
import com.zhouzhou.listener.FriendListener;
import com.zhouzhou.listener.GroupListener;
import kotlinx.serialization.json.JsonObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping
public class RobotEventController {

    @Resource
    private FriendListener friendListener;

    @Resource
    private GroupListener groupListener;

    @PostMapping
    public void receiveEvent(HttpServletRequest request, @RequestBody EventInfo eventInfo) throws IOException, APINoHandleException {
        //如果是消息
        if (eventInfo.getPost_type().equals("message")){
            System.out.println(eventInfo);
            //如果是私人消息
            if(eventInfo.getMessage_type().equals("private")){
                friendListener.addListen(eventInfo);
            }
            //如果是群消息
            else if(eventInfo.getMessage_type().equals("group")){
                groupListener.addListen(eventInfo);
            }
        }

    }
}
