package com.zhouzhou.feignClient;

import com.zhouzhou.dao.dto.AppearanceDto;
import com.zhouzhou.dao.dto.DailyTaskDto;
import com.zhouzhou.dao.dto.GoldPriceDto;
import com.zhouzhou.dao.dto.MeetingPreDto;
import com.zhouzhou.dao.form.GetAppearanceForm;
import com.zhouzhou.dao.form.GetDailyTaskForm;
import com.zhouzhou.dao.form.GetGoldPriceForm;
import com.zhouzhou.dao.form.GetMeetingPreForm;
import com.zhouzhou.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "JX3APIClient",url = "https://www.jx3api.com")
public interface JX3APIClient {

    @RequestMapping("/data/active/current")
    Result<DailyTaskDto> getDailyTask(@RequestBody GetDailyTaskForm getDailyTaskForm);

    @RequestMapping("/data/trade/feiniu")
    Result<List<List<AppearanceDto>>> getAppearancePrice(@RequestBody GetAppearanceForm getAppearanceForm);

    @RequestMapping("/data/trade/demon")
    Result<List<GoldPriceDto>> getGoldPrice(@RequestBody GetGoldPriceForm getGoldPriceForm);

    @RequestMapping("/data/lucky/sub/require")
    Result<MeetingPreDto> getMeetingPre(@RequestBody GetMeetingPreForm getMeetingPreForm);
}
