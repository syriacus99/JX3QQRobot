package com.zhouzhou.feignClient;

import com.zhouzhou.dao.dto.*;
import com.zhouzhou.dao.form.*;
import com.zhouzhou.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "JX3APIClient",url = "https://api.jx3api.com")
public interface JX3APIClient {

    //获取日常
    @RequestMapping("/data/active/current")
    Result<DailyTaskDto> getDailyTask(@RequestBody GetDailyTaskForm getDailyTaskForm);

    //获取物价
    @RequestMapping("/data/trade/feiniu")
    Result<List<List<AppearanceDto>>> getAppearancePrice(@RequestBody GetAppearanceForm getAppearanceForm);

    //获取金价
    @RequestMapping("/data/trade/demon")
    Result<List<GoldPriceDto>> getGoldPrice(@RequestBody GetGoldPriceForm getGoldPriceForm);

    //获取奇遇前置
    @RequestMapping("/data/lucky/sub/require")
    Result<MeetingPreDto> getMeetingPre(@RequestBody GetMeetingPreForm getMeetingPreForm);

    //奇遇后续
    @RequestMapping("/data/lucky/sub/strategy")
    Result<MeetingStrategyDto> getMeetingStrategy(@RequestBody GetMeetingStrategyForm getMeetingStrategyForm);

    //公告
    @RequestMapping("/data/web/announce")
    Result<List<AnnounceDto>> getAnnounce(@RequestBody GetAnnounceForm getAnnounceForm);

    //获取开服状态
    @RequestMapping("/data/server/status")
    Result<GameServiceStatusDto> getGameServiceStatus(@RequestBody GetGameServiceStatusForm getGameServiceStatusForm);

}
