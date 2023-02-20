package com.zhouzhou.dao.dto;

import lombok.Data;

@Data
public class MeetingPreDto {
    // 奇遇名字
    private String name;
    // 触发方式
    private String means;
    // 前置
    private String require;
    // 其他要求
    private String maybe;
    // 奖励
    private String reward;
    // 图片 url
    private String url;

    @Override
    public String toString() {
        return "奇遇:'" + name + "',\n" +
                "触发方式:'" + means + "',\n" +
                "前置:'" + require + "',\n" +
                "其他要求:'" + maybe + "',\n" +
                "奖励:'" + reward + "',\n";
    }
}
