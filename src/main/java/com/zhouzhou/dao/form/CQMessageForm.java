package com.zhouzhou.dao.form;

import lombok.Data;

@Data
public class CQMessageForm<T> {
    //CQ 消息类型
    private String type;
    // data
    private T data;
}
