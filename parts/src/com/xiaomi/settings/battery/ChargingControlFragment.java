/*
 * Copyright (C) 2023-2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xiaomi.settings.battery;

import android.os.Bundle;
import android.content.Intent;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SeekBarPreference;
import com.xiaomi.settings.R;

public class ChargingControlFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private SeekBarPreference mLimitPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.charging_control_settings);

        mLimitPref = findPreference(BatteryUtils.PREF_CHARGING_LIMIT);
        mLimitPref.setOnPreferenceChangeListener(this);
        
        updateSummary(mLimitPref.getValue());

        findPreference(BatteryUtils.PREF_CHARGING_CTRL).setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mLimitPref) {
            updateSummary((int) newValue);
        }

        getContext().startService(new Intent(getContext(), ChargingLimitService.class));
        return true;
    }

    private void updateSummary(int value) {
        mLimitPref.setSummary(String.format(
                getString(R.string.charging_limit_summary), value));
    }
}
