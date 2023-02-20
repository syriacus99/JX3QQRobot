package com.zhouzhou.dao.dto;

import lombok.Data;

@Data
public class AppearanceDto {
    private String id;
    // 区
    private String zone;
    // 服务器
    private String server;
    // 收/卖
    private String sales;
    // 价格
    private String value;
    // 日期
    private String date;

    @Override
    public String toString() {
        return  " 区='" + zone + "',\n" +
                " 服务器='" + server + "',\n" +
                " 收/买='" + sales + "',\n" +
                " 价格='" + value + "',\n" +
                " 时间='" + date + "',\n" ;
    }
}
