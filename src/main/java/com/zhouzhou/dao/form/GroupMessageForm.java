package com.zhouzhou.dao.form;

import lombok.Data;

@Data
public class GroupMessageForm<T> {
    private Long group_id;
    private T message;
    private String auto_escape="false";
}
