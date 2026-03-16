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
import com.xiaomi.settings.R;

public class ChargingControlFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.charging_control_settings);

        findPreference(BatteryUtils.PREF_CHARGING_CTRL).setOnPreferenceChangeListener(this);
        findPreference(BatteryUtils.PREF_CHARGING_LIMIT).setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Intent intent = new Intent(getContext(), ChargingLimitService.class);
        getContext().startService(intent);
        return true;
    }
}
