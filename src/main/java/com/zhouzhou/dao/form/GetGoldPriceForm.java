package com.zhouzhou.dao.form;

import lombok.Data;

@Data
public class GetGoldPriceForm {
    //服务器
    private String server;
    //返回数据长度限制
    private Integer limit=1;
}
