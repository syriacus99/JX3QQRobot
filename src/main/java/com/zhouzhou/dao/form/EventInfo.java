package com.zhouzhou.dao.form;

import lombok.Data;

@Data
public class EventInfo {
    private String post_type;
    private String message_type;
    private String time;
    private String self_id;
    private String sub_type;
    private Sender sender;
    private String message_id;
    private Long user_id;
    private String target_id;
    private String message;
    private String raw_message;
    private Long group_id;
    private String font;
}
