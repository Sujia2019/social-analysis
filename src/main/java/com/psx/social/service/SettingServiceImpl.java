package com.psx.social.service;

import com.psx.social.dao.SettingsMapper;
import com.psx.social.entity.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    SettingsMapper settingsMapper;

    @Override
    public Settings getUserSettings(String account) {
        return settingsMapper.findByAccount(account);
    }

    @Override
    public int updateUserSettings(Settings settings) {
        return settingsMapper.update(settings);
    }
}
