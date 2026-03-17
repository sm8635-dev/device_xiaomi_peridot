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
    private boolean mReceiverRegistered = false;

    private final BroadcastReceiver mBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                updateChargingState(context, intent);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, filter);
        updateChargingState(this, batteryStatus);

        if (!mReceiverRegistered) {
            getApplicationContext().registerReceiver(mBatteryReceiver, filter);
            mReceiverRegistered = true;
        }
        return START_STICKY;
    }

    private void updateChargingState(Context context, Intent intent) {
        if (intent == null) return;

        Context storageContext = context.createDeviceProtectedStorageContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(storageContext);
        
        boolean isEnabled = prefs.getBoolean(BatteryUtils.PREF_CHARGING_CTRL, false);
        if (!isEnabled) {
            BatteryUtils.setChargingSuspendAsync(false);
            return;
        }

        int limit = prefs.getInt(BatteryUtils.PREF_CHARGING_LIMIT, 80);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        
        if (level != -1) {
            BatteryUtils.setChargingSuspendAsync(level >= limit);
        }
    }

    @Override
    public void onDestroy() {
        if (mReceiverRegistered) {
            getApplicationContext().unregisterReceiver(mBatteryReceiver);
            mReceiverRegistered = false;
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}
