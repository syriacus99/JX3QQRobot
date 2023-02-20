package com.zhouzhou.dao.MessageType;

import lombok.Data;

@Data
// https://docs.go-cqhttp.org/cqcode/#%E5%9B%BE%E7%89%87
public class CQTypeImage {
    //图片文件名
    private String file;
    //图片类型, flash 表示闪照, show 表示秀图, 默认普通图片
    private String type;
    //图片子类型, 只出现在群聊.
    private Integer subType=0;
    //图片 URL
    private String url;
    //只在通过网络 URL 发送时有效, 表示是否使用已缓存的文件, 默认 1
    private Integer cache = 1;
    //发送秀图时的特效id, 默认为40000
    private Integer id=40000;
    //通过网络下载图片时的线程数, 默认单线程. (在资源不支持并发时会自动处理)
    private Integer c = 3;
}
