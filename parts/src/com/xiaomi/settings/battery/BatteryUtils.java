/*
 * Copyright (C) 2023-2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xiaomi.settings.battery;

import android.os.SystemProperties;

public class BatteryUtils {
    public static final String PREF_CHARGING_CTRL = "charging_control";
    public static final String PREF_CHARGING_LIMIT = "charging_limit";
    public static final String PROPERTY_SUSPEND = "sys.battery.suspend";

    public static void setChargingSuspend(boolean suspend) {
        SystemProperties.set(PROPERTY_SUSPEND, suspend ? "1" : "0");
    }
}
