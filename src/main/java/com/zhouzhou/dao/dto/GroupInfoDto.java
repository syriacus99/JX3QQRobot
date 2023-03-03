package com.zhouzhou.dao.dto;

import lombok.Data;

@Data
public class GroupInfoDto {
    // 群号
    private Long group_id;
    // 群名
    private String group_name;
    // 群人数
    private Integer member_count;
    // 群最大人数
    private Integer max_member_count;
}
