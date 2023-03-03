package com.zhouzhou.dao.dto;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class GameServiceOpenPushDataDto {
    private String server;
    private Integer status;

    @Override
    public String toString() {
        String statusSC = (status == 1?"开服了":"维护了");
        return server + "\n" +
                statusSC;
    }
}
