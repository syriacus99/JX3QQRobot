package com.zhouzhou.feignClient;

import com.alibaba.fastjson.JSONObject;
import com.zhouzhou.dao.form.GroupMessageForm;
import com.zhouzhou.dao.form.PrivateMessageForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="gocqClient",url = "http://127.0.0.1:5700")
public interface GocqClient {

    @PostMapping("/send_private_msg")
    public JSONObject send_private_msg(@RequestBody PrivateMessageForm privateMessageForm);

    @PostMapping("/send_group_msg")
    public JSONObject send_group_msg(@RequestBody GroupMessageForm groupMessageForm);

}
