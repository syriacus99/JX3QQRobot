package com.zhouzhou.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.dao.dto.GroupInfoDto;
import com.zhouzhou.feignClient.GocqClient;
import com.zhouzhou.util.MyWebSocketClient;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.net.ConnectException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationInitConfig implements ApplicationRunner {

    @Resource
    private GocqClient gocqClient;

    @Resource
    private GlobalVariable globalVariable;

    @Value("${JX3API.socket}")
    private String socketURI;

    @Override
    public void run(ApplicationArguments args) throws ConnectException,Exception {
        //JSONArray data = null;
        JSONObject resp = null;
        int count = 0;
        while(resp ==null && count<1000){
            try {
                resp = gocqClient.get_group_list();
            }catch (ConnectException e){
                //System.out.println("等待gocqhttp链接");
            }catch (RetryableException e){
                System.out.println("等待gocqhttp链接");
            }
            Thread.sleep(800);
            count++;
        }
        String data = JSON.toJSONString(resp.get("data"));
        List<GroupInfoDto> group_list = JSONArray.parseArray(data,GroupInfoDto.class);
        List<Long> collect = group_list.stream().map(GroupInfoDto::getGroup_id).collect(Collectors.toList());
        System.out.println(collect);
        globalVariable.setGroupIdList(collect);
        System.out.println("获取群id成功");
        MyWebSocketClient myWebSocketClient = new MyWebSocketClient(new URI(socketURI));
        myWebSocketClient.connect();
    }
}
