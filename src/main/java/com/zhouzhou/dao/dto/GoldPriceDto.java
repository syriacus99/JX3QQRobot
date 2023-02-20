package com.zhouzhou.dao.dto;

import lombok.Data;

@Data
public class GoldPriceDto {
    private String id;
    //万宝楼金价
    private String wanbaolou;
    //贴吧金价
    private String tieba;
    //dd373
    private String dd373;
    //uu898
    private String uu898;
    //时间
    private String date;

    @Override
    public String toString() {
        return "万宝楼：'" + wanbaolou + "',\n" +
                ", 贴吧：'" + tieba + "',\n" +
                ", dd373：'" + dd373 + "',\n" +
                ", uu898：'" + uu898 + "',\n" +
                ", 日期：'" + date + "',\n";
    }
}
