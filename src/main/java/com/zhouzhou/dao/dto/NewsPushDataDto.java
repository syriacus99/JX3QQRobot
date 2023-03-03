package com.zhouzhou.dao.dto;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class NewsPushDataDto {
    // 消息类型
    private String type;
    // 标题
    private String title;
    // 地址
    private String url;
    // 日期
    private String date;

    @Override
    public String toString() {
        return new StringJoiner(",\n")
                .add("消息类型:" + type)
                .add("标题:" + title)
                .add("地址:" + url)
                .add("日期:" + date)
                .toString();
    }
}
