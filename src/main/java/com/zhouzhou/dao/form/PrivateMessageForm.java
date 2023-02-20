package com.zhouzhou.dao.form;

import lombok.Data;

@Data
public class PrivateMessageForm<T> {
    private Long user_id;
    private T message;
    private String auto_escape="false";
}
