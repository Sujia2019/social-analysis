package com.psx.social.controller;

import com.psx.social.entity.Settings;
import com.psx.social.service.SettingService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("设置")
@RestController
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    SettingService settingService;

    @ApiOperation("保存设置")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> saveSettings(@RequestBody Settings settings) {
        settingService.updateUserSettings(settings);
        return new ReturnT<>(Constants.SUCCESS, "保存成功");
    }

    @ApiOperation("查看设置")
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> search(@RequestParam String account) {
        return new ReturnT<>(Constants.SUCCESS, settingService.getUserSettings(account));
    }
}
