package com.zhouzhou.dao.dto;

import lombok.Data;

@Data
public class JX3MacroDto {
    private Integer id;
    //职业名称
    private String name;
    //奇穴
    private String qixue;
    //宏
    private String macro;
    private String time;
}
