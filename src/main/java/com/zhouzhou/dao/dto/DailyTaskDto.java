package com.zhouzhou.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class DailyTaskDto {
    //日期
    private String date;
    //周几
    private String week;
    //大战
    private String war;
    //战场
    private String battle;
    //阵营日常
    private String camp;
    //宠物up
    private List<String> lucky;
    //门派时间
    private String school;
    //驰援
    private String relief;
    //美人计
    private String draw = "日常 【区服】 可查询美人计";
    //0 门派时间，1 5人周长，2 10人周常
    private List<String> team;

    @Override
    public String toString() {
        return "今天是:" + this.getDate() +"\r\n" +
                "PvP日常：" + this.getCamp() +"\r\n" +
                "战场: " + this.getBattle() + "\r\n" +
                "大战: " + this.getWar() + "\r\n" +
                "美人图: " + this.getDraw() +"\r\n" +
                "宠物up: " + this.getLucky() + "\r\n" +
                "门派事件: " + this.getSchool() + "\r\n" +
                "驰援: " + this.getRelief() +"\r\n" +
                "世界任务：" + this.getTeam().get(0) +"\r\n" +
                "5人周常： " + this.getTeam().get(1) +"\r\n" +
                "10人周常： " + this.getTeam().get(2) +"\r\n";
    }
}
