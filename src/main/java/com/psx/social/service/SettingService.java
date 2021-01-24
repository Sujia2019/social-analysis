package com.psx.social.service;

import com.psx.social.entity.Settings;

public interface SettingService {
    Settings getUserSettings(String account);

    int updateUserSettings(Settings settings);
}
