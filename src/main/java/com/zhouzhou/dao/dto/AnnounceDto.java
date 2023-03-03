package com.zhouzhou.dao.dto;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class AnnounceDto {
    private Integer id;
    private String value;
    // 消息类型如：官方公告
    private String type;
    // 消息标题
    private String title;
    // 日期
    private String date;
    // 地址
    private String url;

    @Override
    public String toString() {
        return new StringJoiner(",\n")
                .add("公告类型:" + type)
                .add("消息标题:" + title)
                .add("日期:" + date)
                .add("地址:" + url)
                .toString();
    }
}
