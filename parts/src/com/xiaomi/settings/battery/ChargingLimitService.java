/*
 * Copyright (C) 2023-2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xiaomi.settings.battery;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

public class ChargingLimitService extends Service {
    private final BroadcastReceiver mBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateChargingState(context, intent);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryReceiver, filter);
        return START_STICKY;
    }

    private void updateChargingState(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isEnabled = prefs.getBoolean(BatteryUtils.PREF_CHARGING_CTRL, false);
        int limit = prefs.getInt(BatteryUtils.PREF_CHARGING_LIMIT, 80);

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isPlugged = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                          status == BatteryManager.BATTERY_STATUS_FULL;

        if (isEnabled && isPlugged && level >= limit) {
            BatteryUtils.setChargingSuspend(true);
        } else {
            BatteryUtils.setChargingSuspend(false);
        }
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
