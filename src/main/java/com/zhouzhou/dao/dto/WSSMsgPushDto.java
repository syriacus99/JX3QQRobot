package com.zhouzhou.dao.dto;

import lombok.Data;

@Data
public class WSSMsgPushDto {
    // 2001开服维护 2002新闻
    private Integer action;
    private Object data;
}
