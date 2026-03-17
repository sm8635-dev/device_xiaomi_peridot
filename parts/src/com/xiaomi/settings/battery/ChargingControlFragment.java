/*
 * Copyright (C) 2023-2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xiaomi.settings.battery;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.SeekBarPreference;
import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.SettingsBasePreferenceFragment;
import com.xiaomi.settings.R;

public class ChargingControlFragment extends SettingsBasePreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SeekBarPreference mLimitPref;
    private MainSwitchPreference mMasterSwitch;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.charging_control_settings);

        mLimitPref = findPreference(BatteryUtils.PREF_CHARGING_LIMIT);
        mLimitPref.setOnPreferenceChangeListener(this);
        updateSummary(mLimitPref.getValue());

        mMasterSwitch = findPreference(BatteryUtils.PREF_CHARGING_CTRL);
        mMasterSwitch.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean isEnabled = mMasterSwitch.isChecked();
        int limit = mLimitPref.getValue();

        if (preference == mMasterSwitch) {
            isEnabled = (boolean) newValue;
        } else if (preference == mLimitPref) {
            limit = (int) newValue;
            updateSummary(limit);
        }

        int currentLevel = getBatteryLevel();
        boolean shouldSuspend = isEnabled && (currentLevel >= limit);
        BatteryUtils.setChargingSuspendAsync(shouldSuspend);

        if (getContext() != null) {
            getContext().startService(new Intent(getContext(), ChargingLimitService.class));
        }
        return true;
    }

    private int getBatteryLevel() {
        BatteryManager bm = (BatteryManager) getContext().getSystemService(Context.BATTERY_SERVICE);
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    private void updateSummary(int value) {
        mLimitPref.setSummary(String.format(
                getString(R.string.charging_limit_summary), value));
    }
}
