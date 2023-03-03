package com.zhouzhou.dao.dto;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class GameServiceStatusDto {
    // 区
    private String zone;
    // 服武器
    private String server;
    // 状态
    private String status;

    @Override
    public String toString() {
        return new StringJoiner(",\n")
                .add(zone)
                .add(server)
                .add("服务器状态:" + status)
                .toString();
    }
}
